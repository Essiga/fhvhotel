package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.application.dto.RoomPriceDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.GuestForm;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/booking")
public class BookingRestController {

    @Autowired
    CreateGuestService createGuestService;

    @Autowired
    CreateBookingService createBookingService;


    private static final String CREATE_BOOKING = "/createBooking";
    private static final String GET_ALL_ROOM_PRICES = "/getRoomPrices";


    @GetMapping(GET_ALL_ROOM_PRICES)
    public RoomPriceDTO getRoomPrices() {

        return new RoomPriceDTO(
                RoomCategory.SINGLE.getPrice(),
                RoomCategory.DOUBLE.getPrice(),
                RoomCategory.SUPERIOR.getPrice()
                );
    }

    @PostMapping(CREATE_BOOKING)
    public void createBooking(@RequestBody BookingDataDTO bookingData) {

        GuestForm guestForm = GuestForm.guestFormFromBookingData(bookingData);
        GuestId guestId = createGuestService.createGuest(guestForm);

        BookingForm bookingForm = BookingForm.bookingFormFromBookingData(bookingData);
        createBookingService.createBooking(bookingForm, guestId);
    }
}
