package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.application.api.ViewGuestService;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.application.dto.RoomPriceDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestType;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.GuestForm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RestApiTests
{
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @LocalServerPort
    private int port;


    @Test
    public void given_nothing_when_getroomprices_then_returnroomprices() {

        //when
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/rest/booking/getRoomPrices").build().encode().toUri();
        RoomPriceDTO roomPriceDTO = this.restTemplate.getForObject(uri, RoomPriceDTO.class);

        // then
        assertEquals(RoomCategory.SINGLE.getPrice(), roomPriceDTO.getSingleRoomPrice());
        assertEquals(RoomCategory.DOUBLE.getPrice(), roomPriceDTO.getDoubleRoomPrice());
        assertEquals(RoomCategory.SUPERIOR.getPrice(), roomPriceDTO.getSuperiorRoomPrice());
    }

    @Test
    public void given_guestdto_when_createGuest_then_guestcreated() {
        //given
        GuestDTO guestExpected = GuestDTO.builder()
            .firstName("Max")
            .lastName("Mustermann")
            .streetAddress("Musterstraße 1")
            .zip("6850")
            .city("Dornbirn")
            .country("Austria")
            .phoneNumber("85525")
            .email("max.mustermann@gmail.com")
            .build();

        //when
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/rest/booking/createGuest").build().encode().toUri();
        GuestId guestIdActual = this.restTemplate.postForObject(uri, guestExpected, GuestId.class);

        Optional<Guest> actualGuestOpt = guestRepository.findGuestById(guestIdActual);

        //then
        assertTrue(actualGuestOpt.isPresent());
        Guest actualGuest = actualGuestOpt.get();

        assertEquals(guestIdActual.getGuestId(), actualGuest.getGuestId().getGuestId());
        assertEquals(guestExpected.getFirstName(), actualGuest.getFirstName());
        assertEquals(guestExpected.getLastName(), actualGuest.getLastName());
        assertEquals(guestExpected.getStreetAddress(), actualGuest.getAddress().getStreet());
        assertEquals(guestExpected.getZip(), actualGuest.getAddress().getZip());
        assertEquals(guestExpected.getCity(), actualGuest.getAddress().getCity());
        assertEquals(guestExpected.getCountry(), actualGuest.getAddress().getCountry());
        assertEquals(guestExpected.getPhoneNumber(), actualGuest.getPhoneNumber());
        assertEquals(guestExpected.getEmail(), actualGuest.getEmail());
    }

    @Test
    public void given_bookingdatadto_when_createbooking_then_bookingcreated() {

        //given
        BookingDataDTO bookingExpected = BookingDataDTO.builder()
            .guestId(new GuestId(UUID.randomUUID()))
            .singleRoomCount(1)
            .doubleRoomCount(2)
            .superiorRoomCount(3)
            .checkInDate("2022-01-14")
            .checkOutDate("2022-01-15")
            .build();

        //when
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/rest/booking/createBooking").build().encode().toUri();
        BookingId bookingIdActual = this.restTemplate.postForObject(uri, bookingExpected, BookingId.class);

        Optional<Booking> actualBookingOpt = bookingRepository.findBookingById(bookingIdActual);

        // then
        assertTrue(actualBookingOpt.isPresent());
        Booking actualBooking = actualBookingOpt.get();

        assertEquals(bookingIdActual.getBookingId(), actualBooking.getBookingId().getBookingId());
        assertEquals(bookingExpected.getSingleRoomCount(), actualBooking.getSingleRoom());
        assertEquals(bookingExpected.getDoubleRoomCount(), actualBooking.getDoubleRoom());
        assertEquals(bookingExpected.getSuperiorRoomCount(), actualBooking.getSuperiorRoom());
        assertEquals(LocalDate.parse(bookingExpected.getCheckInDate()), actualBooking.getCheckInDate());
        assertEquals(LocalDate.parse(bookingExpected.getCheckOutDate()), actualBooking.getCheckOutDate());
    }
}
