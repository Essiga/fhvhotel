package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import at.fhv.hotelsoftware.view.form.FreeRoomListWrapper;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.GuestForm;
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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

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
    CreateGuestService createGuestService;

    @Autowired
    ViewGuestService viewGuestService;

    @Autowired
    ViewInvoiceService viewInvoiceService;

    @Autowired
    CheckOutService checkOutService;

    @Autowired
    CreateInvoiceService createInvoiceService;

    //TODO: remove, only for testing/debugging
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    GuestRepository guestRepository;





    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_GUEST_URL = "/createGuest";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String EXTRA_SERVICE_URL = "/extraService";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";
    private static final String CHECK_IN_GUEST_OVERVIEW = "/checkInGuestOverview";
    private static final String CHECK_IN_GUEST= "/checkInGuest";
    private static final String CREATE_DUMMY_DATA = "/createDummyData";
    private static final String CHECK_OUT_GUEST_OVERVIEW = "/checkOutGuestOverview";
    private static final String CHECK_OUT_GUEST = "/checkOutGuest";
    private static final String ERROR_URL = "/showErrorPage";
    private static final String CREATE_INVOICE = "/createInvoice";
    private static final String SUBMIT_INVOICE = "/submitInvoice";
    private static final String CREATE_INVOICE_PDF ="/pdfInvoice";


    private static final String ERROR_PAGE = "errorPage";

    @Transactional
    @GetMapping(CREATE_DUMMY_DATA)
    public ModelAndView createDummyData(Model model){
        Room singleRoom[] = new Room[10];
        Room doubleRoom[] = new Room[10];
        Room luxusRoom[] = new Room[10];

        for (int i = 0; i < singleRoom.length; i++) {
             singleRoom [i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SINGLE).
                    roomNumber(100 + i).build();
            viewRoomService.createRoom(singleRoom [i]);

            doubleRoom [i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.DOUBLE).
                    roomNumber(200 + i).build();
            viewRoomService.createRoom(doubleRoom [i]);

            luxusRoom [i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SUPERIOR).
                    roomNumber(300 + i).build();
            viewRoomService.createRoom(luxusRoom [i]);
        }

        GuestId guestId = new GuestId(UUID.randomUUID());
        GuestId guestId2 = new GuestId(UUID.randomUUID());
        GuestId guestId3 = new GuestId(UUID.randomUUID());
        GuestId guestId4 = new GuestId(UUID.randomUUID());

        Guest guest = Guest.builder().guestId(guestId).firstName("Adrian").lastName("Essig").street("Jahngasse 1").city("Dornbirn").zip("6800").country("Austria").phoneNumber("06608371982").email("aes6270@students.fhv.at").build();
        Guest guest2 = Guest.builder().guestId(guestId2).firstName("Fabian").lastName("Egartner").street("Jahngasse 1").city("Dornbirn").zip("6800").country("Austria").phoneNumber("06608371982").email("aes6270@students.fhv.at").build();
        Guest guest3 = Guest.builder().guestId(guestId3).firstName("Tobias").lastName("Kurz").street("Teststraße 1").city("Altach").zip("6844").country("Austria").phoneNumber("06608371982").email("tobias.kurz@students.fhv.at").build();
        Guest guest4 = Guest.builder().guestId(guestId4).firstName("Achim").lastName("Unterkofler").street("Teststraße 1").city("Altach").zip("6844").country("Austria").phoneNumber("06608371982").email("achim.unterkofler@students.fhv.at").build();

        guestRepository.addGuest(guest);
        guestRepository.addGuest(guest2);
        guestRepository.addGuest(guest3);
        guestRepository.addGuest(guest4);

        Booking booking = Booking.builder().bookingId(new BookingId(UUID.randomUUID())).
                guestId(guestId).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(2).
                doubleRoom(1).
                superiorRoom(1).
                voucherCode(new VoucherCode("")).
                build();

        Booking booking2 = Booking.builder().bookingId(new BookingId(UUID.randomUUID())).
                guestId(guestId2).
                bookingStatus(BookingStatus.CONFIRMED).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(0).
                superiorRoom(0).
                voucherCode(new VoucherCode("")).
                build();

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
            List<GuestDTO> checkInGuests = findGuestsForBookings(checkIns);
            List<GuestDTO> checkOutGuests = findGuestsForBookings(checkOuts);

            model.addAttribute("checkInGuests", checkInGuests);
            model.addAttribute("checkOutGuests", checkOutGuests);

        } catch (GuestNotFoundException e) {
            redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("dashboard");
    }

    private List<GuestDTO> findGuestsForBookings(List<BookingDTO> bookings) throws GuestNotFoundException {

        List<GuestDTO> guests = new LinkedList<GuestDTO>();

        for (BookingDTO booking: bookings) {
            guests.add(viewGuestService.findGuestById(booking.getGuestId()));
        }

        return guests;
    }

    @GetMapping(CREATE_GUEST_URL)
    public ModelAndView showGuestForm(Model model) {

        GuestForm guestForm = new GuestForm();
        BookingForm bookingForm = new BookingForm();

        try {
            List<GuestDTO> allGuests = viewGuestService.findAllGuest();
            model.addAttribute("allGuests", allGuests);
        } catch (GuestNotFoundException e) {
            e.printStackTrace();
        }

        model.addAttribute("guestForm", guestForm);
        model.addAttribute("bookingForm", bookingForm);

        return new ModelAndView("createGuest");
    }

    @PostMapping(CHOOSE_ROOM_URL)
    public ModelAndView submitChooseRoom(@ModelAttribute("guestForm") @Valid GuestForm guestForm, BindingResult result,
                                         @ModelAttribute("bookingForm") BookingForm bookingForm,
                                         Model model) {

        if (result.hasErrors()) {
            return new ModelAndView("createGuest");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("guestForm", guestForm);

        return new ModelAndView("chooseRoom");
    }

    @PostMapping(EXTRA_SERVICE_URL)
    public ModelAndView submitExtraService(@ModelAttribute("guestForm") @Valid GuestForm guestForm, BindingResult resultGuest,
                                           @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult resultBooking,
                                           Model model) {

        if (resultGuest.hasErrors()) {
            return new ModelAndView("createGuest");
        }

        if (resultBooking.hasErrors()) {
            return new ModelAndView("chooseRoom");
        }

        if(!validDuration(bookingForm) || !validCategoryCount(bookingForm)) {
            bookingForm.setValidDuration(validDuration(bookingForm));
            bookingForm.setValidCategoryCount(validCategoryCount(bookingForm));

            return new ModelAndView("chooseRoom");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("guestForm", guestForm);

        return new ModelAndView("extraService");
    }

    private boolean validCategoryCount(BookingForm bookingForm){
        return (bookingForm.getSingleRoomCount() + bookingForm.getDoubleRoomCount() + bookingForm.getSuperiorRoomCount()) > 0;
    }

    private boolean validDuration(BookingForm bookingForm){
        return LocalDate.parse(bookingForm.getCheckInDate()).isBefore(LocalDate.parse(bookingForm.getCheckOutDate()));
    }

    @PostMapping(BOOKING_SUMMARY_URL)
    public ModelAndView submitBookingSummary(@ModelAttribute("guestForm") @Valid GuestForm guestForm, BindingResult resultGuest,
                                             @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult resultBooking,
                                             Model model) {

        if (resultGuest.hasErrors()) {
            return new ModelAndView("createGuest");
        }

        if (resultBooking.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {
            return new ModelAndView("chooseRoom");
        }

        model.addAttribute("bookingForm", bookingForm);
        model.addAttribute("guestForm", guestForm);

        return new ModelAndView("bookingSummary");
    }

    @PostMapping(WRITE_BOOKING_IN_DB)
    public ModelAndView writeBookingInDatabase(@ModelAttribute("guestForm") @Valid GuestForm guestForm, BindingResult resultGuest,
                                               @ModelAttribute("bookingForm") @Valid BookingForm bookingForm, BindingResult resultBooking) {

        if (resultGuest.hasErrors()) {
            return new ModelAndView("createGuest");
        }

        if (resultBooking.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {
            return new ModelAndView("chooseRoom");
        }

        GuestId guestId = createGuestService.createGuest(guestForm);
        createBookingService.createBooking(bookingForm, guestId);

        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping  (CHECK_IN_GUEST_OVERVIEW)
    public ModelAndView checkInGuestOverview(@RequestParam("id") String id, Model model) {

        BookingId bookingId = new BookingId(id);

        try {
            List<RoomDTO> freeRoomListForBooking = checkInService.findFreeRoomsForBooking(bookingId);
            FreeRoomListWrapper freeRoomListWrapper = new FreeRoomListWrapper(freeRoomListForBooking);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            GuestDTO guestDTO = viewGuestService.findGuestById(bookingDTO.getGuestId());

            model.addAttribute("guest", guestDTO);
            model.addAttribute("freeRoomListWrapper", freeRoomListWrapper);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("id", bookingId);

        } catch (BookingNotFoundException | GuestNotFoundException | NotEnoughRoomsException e){
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("checkInGuestOverview");
    }

    @PostMapping (CHECK_IN_GUEST)
    public ModelAndView checkInGuest(@ModelAttribute("booking") BookingForm booking,
                                     @ModelAttribute("freeRoomListWrapper") FreeRoomListWrapper freeRoomListWrapper,
                                     Model model) {

        try {
            checkInService.checkIn(booking.getBookingId(), freeRoomListWrapper.getFreeRoomList());

        } catch (RoomNotFoundException | RoomAlreadyOccupiedException | BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("redirect:"+"/");
    }


    //TODO: Add function to get rooms from DB
    @GetMapping(CHECK_OUT_GUEST_OVERVIEW)
    public ModelAndView checkOutGuestOverview(@RequestParam("id") String id, Model model){

        BookingId bookingId = new BookingId(id);

        try {
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            GuestDTO guestDTO = viewGuestService.findGuestById(bookingDTO.getGuestId());

            model.addAttribute("guest", guestDTO);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("booking", bookingDTO);

        } catch (Exception e){
            return new ModelAndView("redirect:"+"/");
        }

        return new ModelAndView("checkOutGuestOverview");
    }

    @PostMapping(CHECK_OUT_GUEST)
    public ModelAndView checkOutGuest(@ModelAttribute("booking") BookingForm booking){

        try {
            checkOutService.checkOut(booking.getBookingId());

        } catch (BookingNotFoundException e) {
            redirectToErrorPage(e.getMessage());
        } catch (RoomNotFoundException e) {
            redirectToErrorPage(e.getMessage());
        } catch (RoomNotOccupiedException e){
            redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping(CREATE_INVOICE)
    public ModelAndView createInvoice(@RequestParam("id") String bookingIdString, Model model){

        try {
            BookingId bookingId = new BookingId(bookingIdString);
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);
            createInvoiceService.createInvoice(bookingId);

            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoiceByBookingId(bookingId);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);

            InvoiceDTO invoice = invoiceDTOs.get(0);
            GuestData guest = invoiceDTOs.get(0).getGuestData();

            model.addAttribute("booking", bookingDTO);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("invoice", invoice);
            model.addAttribute("guest", guest);

        } catch (Exception e){
            return new ModelAndView("createInvoice");
        }

        return new ModelAndView("createInvoice");
    }

    @GetMapping ("/pdfInvoice")
    public void generatePdf(HttpServletResponse response, @RequestParam("id") String bookingIdString, Model model) {


        try {
            BookingId bookingId = new BookingId(bookingIdString);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            List<RoomDTO> roomDTO = viewRoomService.findRoomsByBookingId(bookingId);
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoiceByBookingId(bookingId);

            InvoiceDTO invoice = invoiceDTOs.get(0);
            GuestData guest = invoiceDTOs.get(0).getGuestData();

            model.addAttribute("booking", bookingDTO);
            model.addAttribute("guest",guest);
            model.addAttribute("room", roomDTO);
            model.addAttribute("invoice", invoice);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();

            context.setVariable("room", roomDTO);
            context.setVariable("guest", guest);
            context.setVariable("booking", bookingDTO);
            context.setVariable("invoice", invoice);


            String html = templateEngine.process("templates/invoice", context);

            String fileName = "invoice.pdf";
            response.addHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/pdf");
            OutputStream outputStream = response.getOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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