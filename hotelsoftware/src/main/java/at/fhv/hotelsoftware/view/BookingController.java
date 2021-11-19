package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.view.form.AvailableRoomList;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.CustomerForm;
import at.fhv.hotelsoftware.view.form.RoomForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    CreateBookingService createBookingService;

    @Autowired
    ViewBookingService viewBookingService;

    @Autowired
    CheckInService checkInService;

    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_CUSTOMER_URL = "/createCustomer";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String EXTRA_SERVICE_URL = "/extraService";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";
    private static final String CHECK_IN_GUEST_OVERVIEW = "/checkInGuestOverview";
    private static final String CHECK_IN_GUEST= "/checkInGuest";
    private static final String CREATE_DUMMY_DATA = "/createDummyData";


    private static final String ERROR_VIEW = "errorView";

    @GetMapping(CREATE_DUMMY_DATA)
    public ModelAndView createDummyData(Model model){
        Room room = Room.builder().
                withRoomStatus(RoomStatus.FREE).
                withBookingId(null).
                withRoomCategory(RoomCategory.DOUBLE).
                withRoomNumber(3).build();

        viewBookingService.createRoom(room);
        return new ModelAndView("redirect:/");
    }

    @GetMapping(DASHBOARD_URL)
    public ModelAndView showDashboard(Model model) {
        List<BookingDTO> listOfBookings = viewBookingService.findTodaysCheckIns();
        model.addAttribute("checkIns", listOfBookings);
        List<BookingDTO> listOfCheckouts = viewBookingService.findTodaysCheckOuts();
        model.addAttribute("checkOuts", listOfCheckouts);

        return new ModelAndView("dashboard");
    }


    @GetMapping(CREATE_CUSTOMER_URL)
    public ModelAndView showCustomerForm(Model model) {
        BookingForm bookingForm = new BookingForm();
        CustomerForm customerForm = new CustomerForm();
        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("customerForm", customerForm);
        return new ModelAndView("createCustomer");
    }

    @PostMapping(CHOOSE_ROOM_URL)
    public ModelAndView submitChooseRoom(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result,  @ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("createCustomer");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("customerForm", customerForm);
        return new ModelAndView("chooseRoom");
    }

    private boolean validCategoryCount(BookingForm bookingForm){
        if((bookingForm.getSingleRoomCount() + bookingForm.getDoubleRoomCount() + bookingForm.getLuxusRoomCount()) <= 0){
            return false;
        }
        return true;
    }

    private boolean validDuration(BookingForm bookingForm){
        return LocalDate.parse(bookingForm.getCheckInDate()).isBefore(LocalDate.parse(bookingForm.getCheckOutDate()));
    }

    @PostMapping(EXTRA_SERVICE_URL)
    public ModelAndView submitExtraService(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult result, Model model) {
        if (result.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {//check date and rooms here
            return new ModelAndView("chooseRoom");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("customerForm", customerForm);
        return new ModelAndView("extraService");
    }

    @PostMapping(BOOKING_SUMMARY_URL)
    public ModelAndView submitBookingSummary(@ModelAttribute("customerForm") CustomerForm customerForm, @ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("customerForm", customerForm);
        return new ModelAndView("bookingSummary");
    }

    @PostMapping(WRITE_BOOKING_IN_DB)
    public ModelAndView writeBookingInDatabase(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result, @ModelAttribute("bookingForm") BookingForm bookingForm, Model model) {
        if (result.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {//check date and rooms here
            return new ModelAndView("bookingSummary");
        }
        createBookingService.createBooking(bookingForm, customerForm);
        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping  (CHECK_IN_GUEST_OVERVIEW)
    public ModelAndView checkInGuestOverview(@RequestParam("id") String bookingId, Model model) {

        try {
            List<RoomDTO> rooms = checkInService.findAvailableRooms(bookingId);
            BookingDTO booking = viewBookingService.findBookingById(bookingId);
            AvailableRoomList availableRoomList = new AvailableRoomList(rooms);
            model.addAttribute("bookingForm", booking);
            model.addAttribute("rooms", rooms);
            model.addAttribute("availableRoomList", availableRoomList);
            return new ModelAndView("checkInGuestOverview");
        } catch (BookingNotFoundException e){
            return new ModelAndView("redirect:"+"/");
        }
    }

    //TODO: Add Room assignment
    @PostMapping (CHECK_IN_GUEST)
    public ModelAndView checkInGuest(@ModelAttribute("bookingForm") BookingForm bookingForm, @ModelAttribute("availableRoomList") AvailableRoomList availableRoomList, @ModelAttribute("availableRoom") AvailableRoomList availableRoomList2, Model model) {

        try {
            checkInService.checkIn(bookingForm.getBookingId(), availableRoomList.getRoomDTOs());
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        } catch (RoomAlreadyOccupiedException e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:"+"/");
    }
}