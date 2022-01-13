package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.api.CreateGuestService;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.RoomPriceDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
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
import org.springframework.web.util.UriComponentsBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTests
{
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CreateBookingService createBookingService;

    @MockBean
    private CreateGuestService createGuestService;

    @LocalServerPort
    private int port;

//    public void given_notes_when_fetchallnotesthroughrest_then_returnequalsnotes() throws Exception {
//        // given
//        List<NoteDTO> noteDTOs = Arrays.asList(NoteDTO.create(...), NoteDTO.create(...));
//        Mockito.when(viewNoteService.allNotes()).thenReturn(noteDTOs);
//        // when
//        URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
//                .path("/rest/note/all").build().encode().toUri();
//        NoteDTO[] restNoteTitles = this.restTemplate.getForObject(uri, NoteDTO[].class);
//        // then
//        for (int i = 0; i < restNoteTitles.length; i++) {
//            assertEquals(noteDTOs.get(i), restNoteTitles[i]);
//        }
//    }

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

//    @Test
//    public void given_bookingdata_when_createbooking_then_bookingcreated() {
//
//        //given
//        BookingDataDTO bookingData = new BookingDataDTO(
//            "Company Name",
//            "123456",
//            "Adrian",
//            "Essig",
//            "street",
//            "6850",
//            "Dornbirn",
//            "Austria",
//            "0555555",
//            "test@test.at",
//            1,
//            2,
//            3,
//            "2022-01-13",
//            "2022-01-14"
//        );
//
//        BookingForm bookingForm = BookingForm.fromBookingData(bookingData);
//        GuestForm guestForm = GuestForm.fromBookingData(bookingData);
//
//        //when
//        URI uri = UriComponentsBuilder.fromUriString("http://localhost:" + port)
//                .path("/rest/booking/createBooking").build().encode().toUri();
//        this.restTemplate.postForObject(uri, bookingData, Void.class);
//
//        GuestId guestId = new GuestId(UUID.randomUUID());
//
//        // then
//        Mockito.verify(createGuestService, times(1)).createGuest(guestForm);
//        Mockito.verify(createBookingService, times(1)).createBooking(bookingForm, guestId);
//    }
}
