package at.fhv.hotelsoftware.view;

import java.util.List;
import java.util.Optional;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import at.fhv.hotelsoftware.application.CreateBookingServiceImpl;


@Controller
public class BookingController {

    private static final String ROOM_URL = "/room";
    private static final String CREATE_CUSTOMER_URL = "/createCustomer";
    private static final String EXTRA_SERVICES_URL = "/extraServices";
    private static final String ADD_EXTRA_SERVICES_URL = "/addExtraServices";
    private static final String CREATE_BOOKING_URL = "/createBooking";
    private static final String ERROR_URL = "/error";

    private static final String ERROR_VIEW = "errorView";




    @Autowired
    private CreateBookingService createBookingService;

    @Autowired
    private CheckInService checkInService;

    @GetMapping (CREATE_BOOKING_URL)
    public void createBooking (@RequestParam("customer") String customer, @RequestParam ("id") String id) {
        Booking booking = Booking.builder().withId(id).withCustomer(customer).build();
        createBookingService.createBooking(booking);
    }

    @PostMapping(CREATE_BOOKING_URL)
    public

    @GetMapping(ERROR_URL)
    public String displayError(@RequestParam("message") String message, Model model) {
        model.addAttribute("message", message);
        return ERROR_VIEW;
    }


   /* @GetMapping(EXTRA_SERVICES_URL)
    public String viewExtraService(Model model) {return EXTRA_SERVICES_URL; }

    @PostMapping (ADD_EXTRA_SERVICES_URL)
    public ModelAndView addExtraService (@ModelAttribute AddExtraServiceForm addExtraServiceForm) {}

    @PostMapping (CREATE_CUSTOMER_URL)
    public ModelAndView createCustomer (@)*/

}
