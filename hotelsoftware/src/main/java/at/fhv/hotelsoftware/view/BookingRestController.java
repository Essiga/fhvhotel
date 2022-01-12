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
    ViewRoomService viewRoomService;

    @Autowired
    CreateGuestService createGuestService;

    @Autowired
    CreateBookingService createBookingService;

    private static final String GET_ALL_ROOMS = "/getAllRooms";
    private static final String CREATE_BOOKING = "/createBooking";
    private static final String GET_ALL_ROOM_PRICES = "/getRoomPrices";


    @GetMapping(GET_ALL_ROOMS)
    public List<RoomDTO> getAllRooms() throws RoomNotFoundException {
        return viewRoomService.findAllRooms();
    }

    @GetMapping(GET_ALL_ROOM_PRICES)
    public RoomPriceDTO getRoomPrices() {

        return new RoomPriceDTO(
                RoomCategory.SINGLE.getPrice(),
                RoomCategory.DOUBLE.getPrice(),
                RoomCategory.SUPERIOR.getPrice()
                );
    }

    @PostMapping(CREATE_BOOKING)
    public String createBooking(@RequestBody BookingDataDTO bookingData) {

        GuestForm guestForm = guestFormFromBookingData(bookingData);
        GuestId guestId = createGuestService.createGuest(guestForm);

        BookingForm bookingForm = bookingFormFromBookingData(bookingData);
        createBookingService.createBooking(bookingForm, guestId);

        return "booking created successfully";
    }

    private BookingForm bookingFormFromBookingData(BookingDataDTO bookingData) {

        BookingForm bookingForm = new BookingForm();

        bookingForm.setVoucherCode(bookingData.getVoucher());
        bookingForm.setCheckInDate(bookingData.getCheckInDate());
        bookingForm.setCheckOutDate(bookingData.getCheckOutDate());
        bookingForm.setSingleRoomCount(bookingData.getSingleRoomCount());
        bookingForm.setDoubleRoomCount(bookingData.getDoubleRoomCount());
        bookingForm.setSuperiorRoomCount(bookingData.getSuperiorRoomCount());

        return bookingForm;
    }

    private GuestForm guestFormFromBookingData(BookingDataDTO bookingData) {

        GuestForm guestForm = new GuestForm();

        guestForm.setFirstName(bookingData.getFirstName());
        guestForm.setLastName(bookingData.getLastName());
        guestForm.setStreetAdr(bookingData.getStreetAdr());
        guestForm.setZip(bookingData.getZip());
        guestForm.setCity(bookingData.getCity());
        guestForm.setCountry(bookingData.getCountry());
        guestForm.setPhoneNumber(bookingData.getPhone());
        guestForm.setEmail(bookingData.getEmail());

        return guestForm;
    }
}
