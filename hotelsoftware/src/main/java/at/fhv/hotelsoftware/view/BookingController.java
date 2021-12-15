package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.*;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import at.fhv.hotelsoftware.view.form.FreeRoomListWrapper;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.GuestForm;
import at.fhv.hotelsoftware.view.form.LineItemWrapper;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    ConfirmBookingService confirmBookingService;

    @Autowired
    SplitInvoiceService splitInvoiceService;

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
    private static final String CREATE_INVOICE_PDF = "/pdfInvoice";
    private static final String BOOKING_OVERVIEW = "/bookingOverview";
    private static final String CONFIRM_BOOKING_SUMMARY  = "/confirmSummary";
    private static final String CONFIRM_BOOKING = "/confirmBooking";
    private static final String SPLIT_INVOICE = "/splitInvoice";

    private static final String ERROR_PAGE = "errorPage";

    @Transactional
    @GetMapping(CREATE_DUMMY_DATA)
    public ModelAndView createDummyData(Model model) throws RoomNotFoundException{
        Room singleRoom[] = new Room[10];
        Room doubleRoom[] = new Room[10];
        Room luxusRoom[] = new Room[10];

        for (int i = 0; i < singleRoom.length; i++) {
             singleRoom [i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SINGLE).
                    roomNumber(100 + i).build();
             //TODO: create createRoomService
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
        GuestId guestId5 = new GuestId(UUID.randomUUID());

        Guest guest = Guest.builder().guestId(guestId).firstName("Adrian").lastName("Essig").street("Jahngasse 1").city("Dornbirn").zip("6800").country("Austria").phoneNumber("06608371982").email("aes6270@students.fhv.at").build();
        Guest guest2 = Guest.builder().guestId(guestId2).firstName("Fabian").lastName("Egartner").street("Jahngasse 1").city("Dornbirn").zip("6800").country("Austria").phoneNumber("06608371982").email("aes6270@students.fhv.at").build();
        Guest guest3 = Guest.builder().guestId(guestId3).firstName("Tobias").lastName("Kurz").street("Teststraße 1").city("Altach").zip("6844").country("Austria").phoneNumber("06608371982").email("tobias.kurz@students.fhv.at").build();
        Guest guest4 = Guest.builder().guestId(guestId4).firstName("Achim").lastName("Unterkofler").street("Teststraße 1").city("Altach").zip("6844").country("Austria").phoneNumber("06608371982").email("achim.unterkofler@students.fhv.at").build();
        Guest guest5 = Guest.builder().guestId(guestId5).firstName("Alp").lastName("Arslan").street("Teststraße 1").city("Altach").zip("6844").country("Austria").phoneNumber("06608371982").email("alpi@students.fhv.at").build();

        guestRepository.addGuest(guest);
        guestRepository.addGuest(guest2);
        guestRepository.addGuest(guest3);
        guestRepository.addGuest(guest4);
        guestRepository.addGuest(guest5);

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

        Booking booking3 = Booking.builder().bookingId(new BookingId(UUID.randomUUID())).
                guestId(guestId5).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now().minusDays(2)).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(0).
                superiorRoom(0).
                voucherCode(new VoucherCode("")).
                build();

        Room room = Room.builder().
                roomStatus(RoomStatus.FREE).
                bookingId(booking3.getBookingId()).
                roomCategory(RoomCategory.SINGLE).
                roomNumber(999).build();

        viewRoomService.createRoom(room);


        bookingRepository.addBooking(booking);
        bookingRepository.addBooking(booking2);
        bookingRepository.addBooking(booking3);

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
        } catch (GuestNotFoundException ignored) {

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

    @GetMapping(CHECK_OUT_GUEST_OVERVIEW)
    public ModelAndView checkOutGuestOverview(@RequestParam("id") String id, Model model){

        BookingId bookingId = new BookingId(id);

        try {
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            GuestDTO guestDTO = viewGuestService.findGuestById(bookingDTO.getGuestId());
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);

            if (invoiceDTOs.isEmpty())
            {
                invoiceDTOs.add(createInvoiceService.createInvoice(bookingId));
            }

            model.addAttribute("guest", guestDTO);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("invoices", invoiceDTOs);

        } catch (BookingNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        }
        catch (RoomNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        }
        catch (GuestNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        } catch (InvoiceAlreadyCreatedException e) {
            return redirectToErrorPage(e.getMessage());
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

    @GetMapping("/invoiceSummary")
    public ModelAndView invoiceSummary(@RequestParam("bookingId") String bookingIdString, @RequestParam("invoiceNumber") String invoiceNumberString, Model model){

        BookingId bookingId = new BookingId(bookingIdString);
        InvoiceNumber invoiceNumber = new InvoiceNumber(invoiceNumberString);
        try {
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);
            InvoiceDTO invoiceDTO = findInvoiceByNumber(invoiceNumber, invoiceDTOs);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);

            GuestData guest = invoiceDTO.getGuestData();

            LineItemWrapper lineItemWrapper = new LineItemWrapper(invoiceDTO.getLineItemDTOs());

            model.addAttribute("invoice", invoiceDTO);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("guest", guest);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("lineItemWrapper", lineItemWrapper);

        } catch (BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (InvoiceNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (RoomNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("invoiceSummary");
    }

    private InvoiceDTO findInvoiceByNumber(InvoiceNumber invoiceNumber, List<InvoiceDTO> invoiceDTOs) throws InvoiceNotFoundException {
        for (InvoiceDTO invoiceDTO : invoiceDTOs) {
            if(invoiceDTO.getInvoiceNumber().getInvoiceNumber().equals(invoiceNumber.getInvoiceNumber())){
                return invoiceDTO;
            }
        }
        throw new InvoiceNotFoundException("Invoice with Number " + invoiceNumber.getInvoiceNumber().toString() + " not found.");
    }

    @GetMapping(CREATE_INVOICE)
    public ModelAndView createInvoice(@RequestParam("id") String bookingIdString, Model model){

        try {
            BookingId bookingId = new BookingId(bookingIdString);
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);

            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);

            if (invoiceDTOs.isEmpty())
            {
                invoiceDTOs.add(createInvoiceService.createInvoice(bookingId));
            }

            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            InvoiceDTO invoiceDTO = invoiceDTOs.get(0);

            LineItemWrapper lineItemWrapper = new LineItemWrapper(invoiceDTO.getLineItemDTOs());
            GuestData guest = invoiceDTO.getGuestData();

            model.addAttribute("booking", bookingDTO);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("invoice", invoiceDTO);
            model.addAttribute("guest", guest);
            model.addAttribute("lineItemWrapper", lineItemWrapper);


        } catch (Exception e){
            return new ModelAndView("createInvoice");
        }

        return new ModelAndView("createInvoice");
    }

    @GetMapping ("/pdfInvoice")
    public ModelAndView generatePdf(HttpServletResponse response, @RequestParam("id") String bookingIdString, @RequestParam("invoiceNumber") String invoiceNumberString, Model model) {

        try {
            BookingId bookingId = new BookingId(bookingIdString);
            InvoiceNumber invoiceNumber = new InvoiceNumber(invoiceNumberString);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            List<RoomDTO> roomDTO = viewRoomService.findRoomsByBookingId(bookingId);
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);

            InvoiceDTO invoiceDTO = findInvoiceByNumber(invoiceNumber, invoiceDTOs);
            GuestData guest = invoiceDTO.getGuestData();

            model.addAttribute("booking", bookingDTO);
            model.addAttribute("guest",guest);
            model.addAttribute("room", roomDTO);
            model.addAttribute("invoice", invoiceDTO);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();

            context.setVariable("room", roomDTO);
            context.setVariable("guest", guest);
            context.setVariable("booking", bookingDTO);
            context.setVariable("invoice", invoiceDTO);


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
        } catch (DocumentException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (IOException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (RoomNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (InvoiceNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }
        return null;
    }

    @GetMapping(ERROR_URL)
    private String displayError(@RequestParam("errorMessage") String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return ERROR_PAGE;
    }

        private static ModelAndView redirectToErrorPage (String errorMessage){
            return new ModelAndView("redirect:" + ERROR_URL + "?errorMessage=" + errorMessage);
        }


    @GetMapping ("bookingOverview.html")
    public ModelAndView showBookings(Model model) {

        try {

            List<BookingDTO> allBookings = viewBookingService.findAllBookings();
            model.addAttribute("allBookings", allBookings);


            List<GuestDTO> allGuests = findGuestsForBookings(allBookings);
            model.addAttribute("allGuests", allGuests);

        } catch (GuestNotFoundException | BookingNotFoundException e) {

            return new ModelAndView("redirect:"+"/");
        }

        return new ModelAndView("bookingOverview");
    }

    @GetMapping(CONFIRM_BOOKING_SUMMARY)
    public ModelAndView bookingSummary(@RequestParam("id") String id, Model model){

        BookingId bookingId = new BookingId(id);

        try {
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            GuestDTO guestDTO = viewGuestService.findGuestById(bookingDTO.getGuestId());

            model.addAttribute("guest", guestDTO);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("id", bookingId);

        } catch (BookingNotFoundException | GuestNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("confirmSummary");
    }

    @PostMapping (CONFIRM_BOOKING)
    public ModelAndView confirmBooking(@ModelAttribute("booking") BookingForm booking) {

        try {
            confirmBookingService.confirmBooking(booking.getBookingId());

        } catch (BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("redirect:"+"/");
    }

    @PostMapping (SPLIT_INVOICE)
    public ModelAndView splitInvoice(@ModelAttribute("lineItemWrapper") LineItemWrapper lineItemWrapper,
                                     @ModelAttribute("booking") BookingDTO bookingDTO,
                                     @ModelAttribute("invoice") InvoiceDTO invoiceDTO) {

        List<LineItemDTO> lineItemDTOs = lineItemWrapper.getLineItemList();

        List<LineItem> lineItemsToSplit = lineItemDTOs
                .stream()
                .map(lineItemDTO ->
                        new LineItem(lineItemDTO.getName(),
                                lineItemDTO.getSplitAmount(),
                                lineItemDTO.getDuration(),
                                lineItemDTO.getPrice()))
                .collect(Collectors.toList());


        try {
            splitInvoiceService.splitInvoice(bookingDTO.getBookingId(), invoiceDTO.getInvoiceNumber(), lineItemsToSplit);
        } catch (BookingNotFoundException | InvoiceNotFoundException | NoLineItemsException | LineItemsMismatchException | AllLineItemsRemovedException e) {
            return redirectToErrorPage(e.getMessage());
        }


        return new ModelAndView("redirect:checkOutGuestOverview?id="+bookingDTO.getBookingId().getBookingId().toString());
    }
}