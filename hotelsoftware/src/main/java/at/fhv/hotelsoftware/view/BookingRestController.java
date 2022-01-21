package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.application.dto.RoomPriceDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.GuestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/booking")
public class BookingRestController {

    @Autowired
    private CreateGuestService createGuestService;

    @Autowired
    private CreateBookingService createBookingService;

    @Autowired
    private ViewRoomService viewRoomService;

    private static final String GET_ALL_ROOM_PRICES = "/getRoomPrices";
    private static final String CREATE_GUEST = "/createGuest";
    private static final String CREATE_BOOKING = "/createBooking";
    private static final String GET_TOTAL_ROOM = "/getTotalRoom";

    @PostMapping(GET_TOTAL_ROOM)
    public List<Integer> getTotalRoom(@RequestBody BookingForm bookingForm) throws BookingNotFoundException, RoomNotFoundException {

        LocalDate checkInDate = LocalDate.parse(bookingForm.getCheckInDate());
        LocalDate checkOutDate = LocalDate.parse(bookingForm.getCheckOutDate());

        return viewRoomService.findFreeContingentOfRooms(checkInDate, checkOutDate);
    }

    @GetMapping(GET_ALL_ROOM_PRICES)
    public RoomPriceDTO getRoomPrices() {

        return new RoomPriceDTO(
                RoomCategory.SINGLE.getPrice(),
                RoomCategory.DOUBLE.getPrice(),
                RoomCategory.SUPERIOR.getPrice()
                );
    }

    @PostMapping(CREATE_GUEST)
    public GuestId createGuest(@RequestBody GuestDTO guestDTO) {

        GuestForm guestForm = GuestForm.fromGuestDTO(guestDTO);
        return createGuestService.createGuest(guestForm);
    }

    @PostMapping(CREATE_BOOKING)
    public BookingId createBooking(@RequestBody BookingDataDTO bookingDataDTO) {

        BookingForm bookingForm = BookingForm.fromBookingDataDTO(bookingDataDTO);
        return createBookingService.createBooking(bookingForm, bookingDataDTO.getGuestId());
    }
}
