package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    CreateBookingService createBookingService;

    @Autowired
    ViewBookingService viewBookingService;




    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_CUSTOMER_URL = "/createCustomer";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String EXTRA_SERVICE_URL = "/extraService";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";
    private static final String CHECK_IN_GUEST = "/checkInGuest";
    private static final String CHANGE_STATUS_IN_DB= "/changeStatusInDb";
    private static final String CHANGE_ROOM_STATUS_IN_DB="changeRoomStatusInDb";

    //private static final String ADD_EXTRA_SERVICES_URL = "/addExtraServices";
    //private static final String CREATE_BOOKING_URL = "/createBooking";
    //private static final String ERROR_URL = "/error";

    private static final String ERROR_VIEW = "errorView";

    @GetMapping(DASHBOARD_URL)
    public ModelAndView showDashboard(Model model) {
        List<BookingDTO> listOfBookings = viewBookingService.findTodaysCheckIns();
        model.addAttribute("bookings", listOfBookings);
        return new ModelAndView("dashboard");
    }

    // @PostMapping("/createCustomer")
    // public ModelAndView showCustomerForm(Model model) {
    //     Customer customer = new Customer();
    //     model.addAttribute("customer", customer);
    //     return new ModelAndView("/createCustomer");
    // }

    @GetMapping(CREATE_CUSTOMER_URL)
    public ModelAndView showCustomerForm(Model model) {
        BookingForm bookingForm = new BookingForm();
        model.addAttribute("bookingForm", bookingForm);
        return new ModelAndView("createCustomer");
    }
/*
    @PostMapping("/createCustomer")
    public String submitCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        System.out.println("CUSTOMER" + customer);
        model.addAttribute("customer", customer);
        return "chooseRoom";
    }
*/

    @PostMapping(CHOOSE_ROOM_URL)
    public ModelAndView submitChooseRoom(@ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
        model.addAttribute("bookingForm", bookingForm);
        return new ModelAndView("chooseRoom");
    }


    @PostMapping(EXTRA_SERVICE_URL)
    public ModelAndView submitExtraService(@ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
        model.addAttribute("bookingForm", bookingForm);
        return new ModelAndView("extraService");
    }

    @PostMapping(BOOKING_SUMMARY_URL)
    public ModelAndView submitBookingSummary(@ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
        model.addAttribute("bookingForm", bookingForm);
        return new ModelAndView("bookingSummary");
    }

    @PostMapping(WRITE_BOOKING_IN_DB)
    public ModelAndView writeBookingInDatabase(@ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
        createBookingService.createBooking(bookingForm);
        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping  ("/checkInGuest")
    public ModelAndView checkInGuest(@RequestParam("id") String bookingId, Model model){
        BookingDTO booking = viewBookingService.findBookingById(bookingId);
        model.addAttribute("bookingForm", booking);
        return new ModelAndView("checkInGuest");
    }

    //TODO: create new post function that calls the checkIn function from the application layer. (see writeBookingInDatabase() Function)
    // this functions then needs to be called by the form action in the checkInGuest.html



}