package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.api.ViewGuestService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.FreeRoomListWrapper;
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
public class CheckInController {

    @Autowired
    CheckInService checkInService;

    @Autowired
    ViewBookingService viewBookingService;

    @Autowired
    ViewGuestService viewGuestService;

    private static final String CHECK_IN_GUEST_OVERVIEW = "/checkInGuestOverview";
    private static final String CHECK_IN_GUEST= "/checkInGuest";
    private static final String ERROR_URL = "/showErrorPage";

    @GetMapping(CHECK_IN_GUEST_OVERVIEW)
    public ModelAndView showCheckInGuestOverview(@RequestParam("id") String id, Model model) {

        BookingId bookingId = new BookingId(id);

        try {
            List<RoomDTO> freeRoomListForBooking = checkInService.findFreeRoomsForBooking(bookingId);
            FreeRoomListWrapper freeRoomListWrapper = new FreeRoomListWrapper(freeRoomListForBooking);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            GuestDTO guestDTO = viewGuestService.findGuestById(bookingDTO.getGuestId());

            model.addAttribute("voucherCode", bookingDTO.getVoucherCode().getVoucherCode());
            model.addAttribute("guest", guestDTO);
            model.addAttribute("freeRoomListWrapper", freeRoomListWrapper);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("id", bookingId);

        } catch (BookingNotFoundException | GuestNotFoundException | NotEnoughRoomsException e){
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("checkInGuestOverview");
    }

    @PostMapping(CHECK_IN_GUEST)
    public ModelAndView checkInGuest(@ModelAttribute("booking") BookingForm booking,
                                     @ModelAttribute("freeRoomListWrapper") FreeRoomListWrapper freeRoomListWrapper,
                                     Model model) {

        try {
            checkInService.checkIn(booking.getBookingId(), freeRoomListWrapper.getFreeRoomList());

        } catch (RoomNotFoundException | RoomAlreadyOccupiedException | BookingNotFoundException | RoomCategoryMismatchException | DoubleRoomNumberException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("redirect:"+"/");
    }

    private static ModelAndView redirectToErrorPage (String errorMessage){
        return new ModelAndView("redirect:" + ERROR_URL + "?errorMessage=" + errorMessage);
    }

}
