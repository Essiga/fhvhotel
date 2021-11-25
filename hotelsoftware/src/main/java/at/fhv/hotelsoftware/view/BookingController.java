package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.CustomerDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.CustomerRepository;
import at.fhv.hotelsoftware.domain.model.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.view.form.FreeRoomListWrapper;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.UUID;

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

    //TODO: remove, only for testing/debugging
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_CUSTOMER_URL = "/createCustomer";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String EXTRA_SERVICE_URL = "/extraService";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";
    private static final String CHECK_IN_GUEST_OVERVIEW = "/checkInGuestOverview";
    private static final String CHECK_IN_GUEST= "/checkInGuest";
    private static final String CREATE_DUMMY_DATA = "/createDummyData";
    private static final String ERROR_URL = "/showErrorPage";

    private static final String ERROR_PAGE = "errorPage";

    @Transactional
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
                withRoomCategory(RoomCategory.SUPERIOR).
                withRoomNumber(300).build();

        viewRoomService.createRoom(singleRoom);
        viewRoomService.createRoom(singleRoom2);
        viewRoomService.createRoom(doubleRoom);
        viewRoomService.createRoom(luxusRoom);

        CustomerId customerId = new CustomerId(UUID.randomUUID());
        CustomerId customerId2 = new CustomerId(UUID.randomUUID());
        Customer customer = Customer.builder().customerId(customerId).firstName("Adrian").lastName("Essig").streetAddress("Jahngasse 1").city("Dornbirn").zip("6800").country("Austria").phoneNumber("06608371982").email("aes6270@students.fhv.at").build();
        Customer customer2 = Customer.builder().customerId(customerId2).firstName("Fabian").lastName("Egartner").streetAddress("Jahngasse 1").city("Dornbirn").zip("6800").country("Austria").phoneNumber("06608371982").email("aes6270@students.fhv.at").build();

        customerRepository.addCustomer(customer);
        customerRepository.addCustomer(customer2);

        Booking booking = Booking.builder().withBookingId(new BookingId(UUID.randomUUID())).withCustomerId(customerId).withBookingStatus(BookingStatus.CONFIRMED).withCheckInDate(LocalDate.now()).withCheckOutDate(LocalDate.now()).withSingleRoom(1).withDoubleRoom(0).withLuxusRoom(0).withVoucherCode(new VoucherCode("")).build();
        Booking booking2 = Booking.builder().withBookingId(new BookingId(UUID.randomUUID())).withCustomerId(customerId2).withBookingStatus(BookingStatus.CONFIRMED).withCheckInDate(LocalDate.now()).withCheckOutDate(LocalDate.now()).withSingleRoom(1).withDoubleRoom(0).withLuxusRoom(0).withVoucherCode(new VoucherCode("")).build();



        bookingRepository.addBooking(booking);
        bookingRepository.addBooking(booking2);

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

    @PostMapping(EXTRA_SERVICE_URL)
    public ModelAndView submitExtraService(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult resultCustomer,
                                           @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult resultBooking,
                                           Model model) {

        if (resultCustomer.hasErrors()) {
            return new ModelAndView("createCustomer");
        }
        if (resultBooking.hasErrors()) {

            return new ModelAndView("chooseRoom");
        }
        if(!validDuration(bookingForm) || !validCategoryCount(bookingForm)){
            //TODO: Jonathan setter ok?
            bookingForm.setValidDuration(validDuration(bookingForm));
            bookingForm.setValidCategoryCount(validCategoryCount(bookingForm));
            return new ModelAndView("chooseRoom");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("customerForm", customerForm);

        return new ModelAndView("extraService");
    }

    private boolean validCategoryCount(BookingForm bookingForm){
        return (bookingForm.getSingleRoomCount() + bookingForm.getDoubleRoomCount() + bookingForm.getSuperiorRoomCount()) > 0;
    }

    private boolean validDuration(BookingForm bookingForm){
        return LocalDate.parse(bookingForm.getCheckInDate()).isBefore(LocalDate.parse(bookingForm.getCheckOutDate()));
    }

    @PostMapping(BOOKING_SUMMARY_URL)
    public ModelAndView submitBookingSummary(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult resultCustomer,
                                             @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult resultBooking,
                                             Model model) {

        if (resultCustomer.hasErrors()) {
            return new ModelAndView("createCustomer");
        }

        if (resultBooking.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {
            return new ModelAndView("chooseRoom");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("customerForm", customerForm);
        return new ModelAndView("bookingSummary");
    }

    @PostMapping(WRITE_BOOKING_IN_DB)
    public ModelAndView writeBookingInDatabase(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult resultCustomer,
                                               @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult resultBooking) {

        if (resultCustomer.hasErrors()) {
            return new ModelAndView("createCustomer");
        }

        if (resultBooking.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {
            return new ModelAndView("chooseRoom");
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
            model.addAttribute("id", bookingId);

        } catch (BookingNotFoundException | CustomerNotFoundException | NotEnoughRoomsException e){
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("checkInGuestOverview");
    }

    @PostMapping (CHECK_IN_GUEST)
    public ModelAndView checkInGuest(@ModelAttribute("booking") BookingForm booking, @ModelAttribute("freeRoomListWrapper") FreeRoomListWrapper freeRoomListWrapper, Model model) {

        try {
            checkInService.checkIn(booking.getBookingId(), freeRoomListWrapper.getFreeRoomList());
        } catch (RoomNotFoundException | RoomAlreadyOccupiedException | BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping(ERROR_URL)
    private String displayError(@RequestParam("errorMessage") String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return ERROR_PAGE;
    }

    private static ModelAndView redirectToErrorPage(String errorMessage) {
        return new ModelAndView("redirect:" + ERROR_URL + "?errorMessage=" + errorMessage);
    }
}