package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
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
    public String createBooking(@RequestBody String jsonString) throws JSONException {

        JSONObject bookingData = new JSONObject(jsonString);

        String gname = bookingData.getString("gname");
        String voucher = bookingData.getString("voucher");
        String firstName = bookingData.getString("firstName");
        String lastName = bookingData.getString("lastName");
        String streetAdr = bookingData.getString("streetAdr");
        String zip = bookingData.getString("zip");
        String city = bookingData.getString("city");
        String country = bookingData.getString("country");
        String phone = bookingData.getString("phone");
        String email = bookingData.getString("email");
        int singleRoomCount = bookingData.getInt("singleRoomCount");
        int doubleRoomCount = bookingData.getInt("doubleRoomCount");
        int superiorRoomCount = bookingData.getInt("superiorRoomCount");
        String checkInDate = bookingData.getString("checkInDate");
        String checkOutDate = bookingData.getString("checkOutDate");

        // create Guest
        GuestForm guestForm = new GuestForm();

        guestForm.setFirstName(firstName);
        guestForm.setLastName(lastName);
        guestForm.setStreetAdr(streetAdr);
        guestForm.setZip(zip);
        guestForm.setCity(city);
        guestForm.setCountry(country);
        guestForm.setPhoneNumber(phone);
        guestForm.setEmail(email);

        GuestId guestId = createGuestService.createGuest(guestForm);

        // create Booking
        BookingForm bookingForm = new BookingForm();

        bookingForm.setVoucherCode(voucher);
        bookingForm.setCheckInDate(checkInDate);
        bookingForm.setCheckOutDate(checkOutDate);
        bookingForm.setSingleRoomCount(singleRoomCount);
        bookingForm.setDoubleRoomCount(doubleRoomCount);
        bookingForm.setSuperiorRoomCount(superiorRoomCount);

        createBookingService.createBooking(bookingForm, guestId);

        return "booking created successfully";
    }
}
