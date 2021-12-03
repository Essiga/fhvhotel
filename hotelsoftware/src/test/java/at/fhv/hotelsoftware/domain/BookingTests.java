package at.fhv.hotelsoftware.domain;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.LineItem;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTests {

    @Test
    public void given_existingbookingwithstatusconfirmed_when_checkin_then_bookingstatuscheckedin(){
        //given
        Booking booking = Booking.builder().bookingId(new BookingId(UUID.randomUUID())).bookingStatus(BookingStatus.CONFIRMED).build();

        //when
        booking.checkIn();

        //then
        assertEquals(BookingStatus.CHECKEDIN, booking.getBookingStatus());

    }

    @Test
    public void given_existingbookingwithstatuscheckedin_when_completed_then_bookingstatuscompleted(){
        //given
        Booking booking = Booking.builder().bookingId(new BookingId(UUID.randomUUID())).bookingStatus(BookingStatus.CHECKEDIN).build();

        //when
        booking.complete();

        //then
        assertEquals(BookingStatus.COMPLETED, booking.getBookingStatus());

    }

    @Test
    public void given_existingbooking_when_createinvoice_then_invoicecreated() throws InvoiceAlreadyCreatedException {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());
        Guest guest = Guest.builder().guestId(guestId).
                street("Jahngasse 1").
                zip("6850").city("Dornbirn").
                country("AUT").
                firstName("Tobias").
                lastName("Kurz").
                phoneNumber("066076321").
                email("tku@students.fhv.at").
                build();

        Booking booking = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                bookingStatus(BookingStatus.CHECKEDIN).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                guestId(guestId).build();

        LocalDate invoiceDate = LocalDate.now();
        InvoiceStatus invoiceStatus = InvoiceStatus.OPEN;
        GuestData guestData = GuestData.fromGuest(guest);
        List<LineItem> lineItems = new LinkedList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), 1, RoomCategory.SUPERIOR.getPrice()));



        //when
        Invoice invoice = booking.createInvoice(guest);

        //then
        assertEquals(invoiceDate, invoice.getInvoiceDate());
        assertEquals(invoiceStatus, invoice.getInvoiceStatus());
        assertEquals(guestData, invoice.getGuestData());

        for (int i = 0; i < lineItems.size(); i++){
            assertEquals(lineItems.get(i), invoice.getLineItems().get(i));
        }

    }

}
