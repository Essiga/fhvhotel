package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceTests {


    @Test
    public void given_properties_when_new_invoice_then_reflectsprops(){
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());

        //when
        Invoice invoice = new Invoice(invoiceNumber, bookingId);

        //then
        assertEquals(bookingId, invoice.getBookingId());
        assertEquals(invoiceNumber, invoice.getInvoiceNumber());
    }

    @Test
    public void given_invoice_when_sum_then_reflectpriceofallrooms(){
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().withSingleRoom(1).withDoubleRoom(1).withSuperiorRoom(1).withBookingId(bookingId).build();
        Double expectedPrice = RoomCategory.SINGLE.getPrice() + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();

        Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), bookingId);

        //when
        //TODO: JONATHAN call repository in invoice?
        Double sum = invoice.getSum(booking);

        //then
        assertEquals(expectedPrice, sum);
    }

    @Test
    public void given_invoice_when_getlineitems_then_getalllineitems(){
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().withSingleRoom(1).withDoubleRoom(1).withSuperiorRoom(1).withBookingId(bookingId).build();
        Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), bookingId);

        //when
        List<LineItem> lineItems = invoice.getLineItems();

        //then

        assertEquals(expectedLineItems, lineItems);
    }
}
