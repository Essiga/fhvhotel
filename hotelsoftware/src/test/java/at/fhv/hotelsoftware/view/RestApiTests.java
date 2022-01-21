package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.application.api.ViewGuestService;
import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.application.dto.RoomPriceDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RestApiTests
{
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private RoomRepository roomRepository;

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

        //then
        assertNotNull(guestIdActual);
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

        // then
        assertNotNull(bookingIdActual);
    }

    @Test
    public void given_bookingformandroomsandbookings_when_gettotalroom_then_returntotalroom() {

        //given
        BookingForm bookingForm = BookingForm.builder()
                .checkInDate(LocalDate.now().toString())
                .checkOutDate(LocalDate.now().plusDays(7).toString())
                .build();

        Booking bookingExpected = Booking.builder()
                .checkInDate(LocalDate.now().plusDays(1))
                .checkOutDate(LocalDate.now().plusDays(3))
                .singleRoom(1)
                .doubleRoom(1)
                .superiorRoom(1)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        Booking bookingExpected2 = Booking.builder()
                .checkInDate(LocalDate.now().plusDays(1))
                .checkOutDate(LocalDate.now().plusDays(3))
                .singleRoom(0)
                .doubleRoom(1)
                .superiorRoom(0)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        List<Integer> expectedResults = new LinkedList<>();
        expectedResults.add(9);
        expectedResults.add(8);
        expectedResults.add(9);

        Mockito.when(bookingRepository.findBookingsByDate(LocalDate.now(), LocalDate.now().plusDays(7))).thenReturn(List.of(bookingExpected, bookingExpected2));
        Mockito.when(roomRepository.findAllSingleRoomCount()).thenReturn(10);
        Mockito.when(roomRepository.findAllDoubleRoomCount()).thenReturn(10);
        Mockito.when(roomRepository.findAllSuperiorRoomCount()).thenReturn(10);

        //when
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
                .path("/rest/booking/getTotalRoom").build().encode().toUri();
        List<Integer> roomContingents = this.restTemplate.postForObject(uri, bookingForm, List.class);

        //then
        assertEquals(expectedResults, roomContingents);
    }
}
