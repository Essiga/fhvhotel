package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreateBookingServiceTests {

    @Autowired
    CreateBookingService createBookingService;

    @Captor
    ArgumentCaptor<Booking> bookingCaptor;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void given_bookingform_when_createbooking_then_validbooking(){
        //given
        GuestId guestIdExpected = new GuestId(UUID.randomUUID());

        BookingForm bookingForm = new BookingForm();
        bookingForm.setSingleRoomCount(3);
        bookingForm.setDoubleRoomCount(2);
        bookingForm.setSuperiorRoomCount(1);
        bookingForm.setCheckInDate(LocalDate.now().toString());
        bookingForm.setCheckOutDate(LocalDate.now().plusDays(1).toString());

        //when
        createBookingService.createBooking(bookingForm, guestIdExpected);

        Mockito.verify(bookingRepository).addBooking(bookingCaptor.capture());
        Booking booking = bookingCaptor.getValue();

        //then
        assertEquals(guestIdExpected.getGuestId(), booking.getGuestId().getGuestId());
        assertEquals(bookingForm.getSingleRoomCount(), booking.getSingleRoom());
        assertEquals(bookingForm.getDoubleRoomCount(), booking.getDoubleRoom());
        assertEquals(bookingForm.getSuperiorRoomCount(), booking.getSuperiorRoom());
        assertEquals(LocalDate.parse(bookingForm.getCheckInDate()), booking.getCheckInDate());
        assertEquals(LocalDate.parse(bookingForm.getCheckOutDate()), booking.getCheckOutDate());
    }
}
