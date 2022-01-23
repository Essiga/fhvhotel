package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.*;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.GuestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    CreateGuestService createGuestService;

    @Autowired
    ViewGuestService viewGuestService;


    @Autowired
    ConfirmBookingService confirmBookingService;

    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_GUEST_URL = "/createGuest";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";
    private static final String ERROR_URL = "/showErrorPage";
    private static final String BOOKING_OVERVIEW = "/bookingOverview";
    private static final String CONFIRM_BOOKING_SUMMARY  = "/confirmSummary";
    private static final String CONFIRM_BOOKING = "/confirmBooking";
    private static final String SINGLE_PAGE_APPLICATION = "/spa";
    private static final String LIGHT = "/light";
    private static final String INDEX = "index.html";

    private static final String ERROR_PAGE = "errorPage";


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
    public ModelAndView submitGuestForm(@ModelAttribute("guestForm") @Valid GuestForm guestForm, BindingResult result,
                                        @ModelAttribute("bookingForm") BookingForm bookingForm,
                                        Model model) {

        if (result.hasErrors()) {

            try {
                List<GuestDTO> allGuests = viewGuestService.findAllGuest();
                model.addAttribute("allGuests", allGuests);
                return new ModelAndView(CREATE_GUEST_URL);

            } catch (GuestNotFoundException ignored) {
            }
        }

        double singleRoomPrice = RoomCategory.SINGLE.getPrice();
        double doubleRoomPrice = RoomCategory.DOUBLE.getPrice();
        double superiorRoomPrice = RoomCategory.SUPERIOR.getPrice();

        model.addAttribute("priceSingleRoom", singleRoomPrice);
        model.addAttribute("priceDoubleRoom", doubleRoomPrice);
        model.addAttribute("priceSuperiorRoom", superiorRoomPrice);
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
    public ModelAndView submitChooseRoom(@ModelAttribute("guestForm") @Valid GuestForm guestForm, BindingResult resultGuest,
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

    @GetMapping(ERROR_URL)
    private String displayError(@RequestParam("errorMessage") String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return ERROR_PAGE;
    }

    private static ModelAndView redirectToErrorPage (String errorMessage){
        return new ModelAndView("redirect:" + ERROR_URL + "?errorMessage=" + errorMessage);
    }


    @GetMapping (BOOKING_OVERVIEW)
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
    public ModelAndView showConfirmBookingSummary(@RequestParam("id") String id, Model model){

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

//    @GetMapping (SINGLE_PAGE_APPLICATION)
//    public ModelAndView spa(){
//        return new ModelAndView("redirect:http://localhost:3000");
//    }

    @GetMapping (SINGLE_PAGE_APPLICATION)
    public ModelAndView spa(){
        return new ModelAndView("redirect:" + INDEX);
    }

    @GetMapping (LIGHT)
    public ModelAndView lamp(){

        return new ModelAndView("light");

    }
}