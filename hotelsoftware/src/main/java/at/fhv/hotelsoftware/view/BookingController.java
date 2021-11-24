package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
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
import java.util.List;
import java.util.Random;

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
    CheckOutService checkOutService;

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

        Room singleRoom[] = new Room[10];
        Room doubleRoom[] = new Room[10];
        Room luxusRoom[] = new Room[10];

        for (int i = 0; i < singleRoom.length; i++) {
             singleRoom [i] = Room.builder().
                    withRoomStatus(RoomStatus.FREE).
                    withBookingId(null).
                    withRoomCategory(RoomCategory.SINGLE).
                    withRoomNumber(100 + i).build();
            viewRoomService.createRoom(singleRoom [i]);

            doubleRoom [i] = Room.builder().
                    withRoomStatus(RoomStatus.FREE).
                    withBookingId(null).
                    withRoomCategory(RoomCategory.DOUBLE).
                    withRoomNumber(200 + 1).build();
            viewRoomService.createRoom(doubleRoom [i]);

            luxusRoom [i] = Room.builder().
                    withRoomStatus(RoomStatus.FREE).
                    withBookingId(null).
                    withRoomCategory(RoomCategory.LUXUS).
                    withRoomNumber(300 + i).build();
            viewRoomService.createRoom(luxusRoom [i]);
        }

        return new ModelAndView("redirect:/");
    }

    @GetMapping(DASHBOARD_URL)
    public ModelAndView showDashboard(Model model) {
        List<BookingDTO> listOfCheckIns = viewBookingService.findTodaysCheckIns();
        model.addAttribute("checkIns", listOfCheckIns);
        List<BookingDTO> listOfCheckOuts = viewBookingService.findTodaysCheckOuts();
        model.addAttribute("checkOuts", listOfCheckOuts);

        return new ModelAndView("dashboard");
    }


    @GetMapping(CREATE_CUSTOMER_URL)
    public ModelAndView showCustomerForm(Model model) {

        CustomerForm customerForm = new CustomerForm();
        model.addAttribute("customerForm", customerForm);
        return new ModelAndView("createCustomer");
    }

    @PostMapping(CHOOSE_ROOM_URL)
    public ModelAndView submitChooseRoom(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ModelAndView("createCustomer");
        }

        BookingForm bookingForm = new BookingForm();
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
    public ModelAndView writeBookingInDatabase(@ModelAttribute("customerForm") @Valid CustomerForm customerForm, BindingResult result, @ModelAttribute("bookingForm") BookingForm bookingForm) {
        if (result.hasErrors() || !validDuration(bookingForm) || !validCategoryCount(bookingForm)) {//check date and rooms here
            return new ModelAndView("bookingSummary");
        }
        createBookingService.createBooking(bookingForm, customerForm);
        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping  (CHECK_IN_GUEST_OVERVIEW)
    public ModelAndView checkInGuestOverview(@RequestParam("id") String bookingId, Model model) {

        try {
            List<RoomDTO> freeRoomListForBooking = checkInService.findFreeRoomsForBooking(bookingId);
            FreeRoomListWrapper freeRoomListWrapper = new FreeRoomListWrapper(freeRoomListForBooking);
            BookingDTO booking = viewBookingService.findBookingById(bookingId);

            model.addAttribute("freeRoomListWrapper", freeRoomListWrapper);
            model.addAttribute("booking", booking);

        } catch (BookingNotFoundException e){
            return new ModelAndView("redirect:"+"/");
        } catch (NotEnoughRoomsException e) {
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
}