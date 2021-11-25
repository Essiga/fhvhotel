package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
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
        Booking booking = Booking.builder().withSingleRoom(1).withDoubleRoom(2).withSuperiorRoom(3).withBookingId(bookingId).build();

        List<LineItem> expectedLineItems = new ArrayList<>();
        expectedLineItems.add(new LineItem(RoomCategory.SINGLE, 1, RoomCategory.SINGLE.getPrice()));
        expectedLineItems.add(new LineItem(RoomCategory.DOUBLE, 2, RoomCategory.DOUBLE.getPrice()));
        expectedLineItems.add(new LineItem(RoomCategory.SUPERIOR, 3, RoomCategory.SUPERIOR.getPrice()));

        Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), bookingId, expectedLineItems);

        //when
        List<LineItem> lineItems = invoice.getLineItems();

        //then
        assertEquals(expectedLineItems, lineItems);
    }

    @Test
    public void given_invoice_when_createinvoicefrombooking_then_expectequalsums(){

        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().withSingleRoom(2).withDoubleRoom(1).withSuperiorRoom(1).withBookingId(bookingId).build();

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE, 1, RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR, 1, RoomCategory.SUPERIOR.getPrice()));

        double expectedSum = (RoomCategory.SINGLE.getPrice() * 2) + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();

        //when
        Invoice invoice = Invoice.createInvoiceFromBooking(booking);
        Double sum = invoice.getSum(booking);

        //then
        assertEquals(expectedSum, sum);
    }

    @Test
    public void given_invoice_when_callinggetsumoninvoicewithoutlineitems_then_expectsumsequalminusone(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Invoice invoice = new Invoice(invoiceNumber, bookingId);

        Double expectedSum = -1.0;

        //when
        Double sum = invoice.getSum();

        //then
        assertEquals(expectedSum, sum);
    }
}
