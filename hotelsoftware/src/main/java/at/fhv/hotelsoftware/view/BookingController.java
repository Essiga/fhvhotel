package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.CustomerDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.view.form.FreeRoomListWrapper;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.CustomerForm;
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
import java.util.LinkedList;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    CreateBookingService createBookingService;

    @Autowired
    ViewBookingService viewBookingService;

    @Autowired
    ViewRoomService viewRoomService;

    @Autowired
    CheckInService checkInService;

    @Autowired
    CreateCustomerService createCustomerService;

    @Autowired
    ViewCustomerService viewCustomerService;

    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_CUSTOMER_URL = "/createCustomer";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String EXTRA_SERVICE_URL = "/extraService";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";
    private static final String CHECK_IN_GUEST_OVERVIEW = "/checkInGuestOverview";
    private static final String CHECK_IN_GUEST= "/checkInGuest";
    private static final String CREATE_DUMMY_DATA = "/createDummyData";
    private static final String CHECK_OUT_GUEST_OVERVIEW = "/checkOutGuestOverview";


    private static final String ERROR_VIEW = "errorView";

    @GetMapping(CREATE_DUMMY_DATA)
    public ModelAndView createDummyData(Model model){
        Room singleRoom = Room.builder().
                withRoomStatus(RoomStatus.FREE).
                withBookingId(null).
                withRoomCategory(RoomCategory.SINGLE).
                withRoomNumber(100).build();

        Room singleRoom2 = Room.builder().
                withRoomStatus(RoomStatus.FREE).
                withBookingId(null).
                withRoomCategory(RoomCategory.SINGLE).
                withRoomNumber(101).build();

        Room doubleRoom = Room.builder().
                withRoomStatus(RoomStatus.FREE).
                withBookingId(null).
                withRoomCategory(RoomCategory.DOUBLE).
                withRoomNumber(200).build();

        Room luxusRoom = Room.builder().
                withRoomStatus(RoomStatus.FREE).
                withBookingId(null).
                withRoomCategory(RoomCategory.LUXUS).
                withRoomNumber(300).build();

        viewRoomService.createRoom(singleRoom);
        viewRoomService.createRoom(singleRoom2);
        viewRoomService.createRoom(doubleRoom);
        viewRoomService.createRoom(luxusRoom);
        return new ModelAndView("redirect:/");
    }

    @GetMapping(DASHBOARD_URL)
    public ModelAndView showDashboard(Model model) {
        List<BookingDTO> checkIns = viewBookingService.findTodaysCheckIns();
        model.addAttribute("checkIns", checkIns);
        List<BookingDTO> checkOuts = viewBookingService.findTodaysCheckOuts();
        model.addAttribute("checkOuts", checkOuts);



        try {
            List<CustomerDTO> checkInCustomers = findCustomersForBookings(checkIns);
            List<CustomerDTO> checkOutCustomers = findCustomersForBookings(checkOuts);

            model.addAttribute("checkInCustomers", checkInCustomers);
            model.addAttribute("checkOutCustomers", checkOutCustomers);

        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }





        return new ModelAndView("dashboard");
    }

    private List<CustomerDTO> findCustomersForBookings(List<BookingDTO> bookings) throws CustomerNotFoundException {
        List<CustomerDTO> customers = new LinkedList<CustomerDTO>();
        for (BookingDTO booking: bookings) {


            customers.add(viewCustomerService.findCustomerById(booking.getCustomerId()));

        }
        return customers;
    }

    @GetMapping(CREATE_CUSTOMER_URL)
    public ModelAndView showCustomerForm(Model model) {

        CustomerForm customerForm = new CustomerForm();
        BookingForm bookingForm = new BookingForm();
        model.addAttribute("customerForm", customerForm);
        model.addAttribute("bookingForm", bookingForm);
        return new ModelAndView("createCustomer");
    }

    @PostMapping(CHOOSE_ROOM_URL)
    public ModelAndView submitChooseRoom(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result,
                                         @ModelAttribute("bookingForm") BookingForm bookingForm,
                                         Model model) {
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
        return true;
        //return LocalDate.parse(bookingForm.getCheckInDate()).isBefore(LocalDate.parse(bookingForm.getCheckOutDate()));
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
    public ModelAndView writeBookingInDatabase(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result, @ModelAttribute("bookingForm") BookingForm bookingForm) {
        if (result.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {//check date and rooms here
            return new ModelAndView("bookingSummary");
        }
        CustomerId customerId = createCustomerService.createCustomer(customerForm);
        createBookingService.createBooking(bookingForm, customerId);
        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping  (CHECK_IN_GUEST_OVERVIEW)
    public ModelAndView checkInGuestOverview(@RequestParam("id") String bookingId, Model model) {

        try {
            List<RoomDTO> freeRoomListForBooking = checkInService.findFreeRoomsForBooking(bookingId);
            FreeRoomListWrapper freeRoomListWrapper = new FreeRoomListWrapper(freeRoomListForBooking);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);

            CustomerDTO customerDTO = viewCustomerService.findCustomerById(bookingDTO.getCustomerId());

            model.addAttribute("customer", customerDTO);
            model.addAttribute("freeRoomListWrapper", freeRoomListWrapper);
            model.addAttribute("booking", bookingDTO);

        } catch (BookingNotFoundException e){
            return new ModelAndView("redirect:"+"/");
        } catch (NotEnoughRoomsException e) {
            e.printStackTrace();
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
        }

        return new ModelAndView("checkInGuestOverview");
    }

    @PostMapping (CHECK_IN_GUEST)
    public ModelAndView checkInGuest(@ModelAttribute("booking") BookingForm booking, @ModelAttribute("freeRoomListWrapper") FreeRoomListWrapper freeRoomListWrapper) {

        try {
            checkInService.checkIn(booking.getBookingId(), freeRoomListWrapper.getFreeRoomList());
        } catch (RoomNotFoundException e) {
            e.printStackTrace();
        } catch (RoomAlreadyOccupiedException e) {
            e.printStackTrace();
        } catch (BookingNotFoundException e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:"+"/");
    }


    //TODO: Add function to get rooms from DB
    @GetMapping(CHECK_OUT_GUEST_OVERVIEW)
    public ModelAndView checkOutGuestOverview(@RequestParam("id") String bookingId, Model model){

        try {
            List<RoomDTO> roomDTO = viewRoomService.findRoomByBookingId(bookingId);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            CustomerDTO customerDTO = viewCustomerService.findCustomerById(bookingDTO.getCustomerId());

            model.addAttribute("customer", customerDTO);
            model.addAttribute("rooms", roomDTO);
            model.addAttribute("booking", bookingDTO);
        } catch (Exception e){
            return new ModelAndView("redirect:"+"/");
        }
        return new ModelAndView("checkOutGuestOverview");
    }
}