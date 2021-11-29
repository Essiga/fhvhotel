package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

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
        //TODO: JONATHAN call repository in invoice? Nooo!!
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
        assertEquals(expectedLineItems.size(), lineItems.size());
    }

    @Test
    public void given_invoice_when_createinvoicefrombookingandsum_then_expectequalsums(){

        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().withSingleRoom(2).withDoubleRoom(1).withSuperiorRoom(1).withBookingId(bookingId).build();

        double expectedSum = (RoomCategory.SINGLE.getPrice() * 2) + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();

        //when
        Invoice invoice = Invoice.createInvoiceFromBooking(booking);
        Double sum = invoice.getSum(booking);

        //then
        assertEquals(expectedSum, sum);
    }

    @Test
    public void given_invoice_when_createinvoicefrombookingandsumwithoutparameter_then_expectequalsums(){

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
        Double sum = invoice.getSum();

        //then
        assertEquals(expectedSum, sum);
    }

    @Test
    public void given_invoice_when_callinggetsumoninvoicewithoutlineitems_then_expectsumsequalzero(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Invoice invoice = new Invoice(invoiceNumber, bookingId);

        Double expectedSum = 0.0;

        //when
        Double sum = invoice.getSum();

        //then
        assertEquals(expectedSum, sum);
    }

    @Test
    public void given_invoice_when_addlineitemstoinvoice_then_expectequalsize(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Invoice invoice = new Invoice(invoiceNumber, bookingId);

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR, 3, RoomCategory.SUPERIOR.getPrice()));

        List<LineItem> lineItems2 = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.DOUBLE, 2, RoomCategory.DOUBLE.getPrice()));

        lineItems.addAll(lineItems2);

        //when
        invoice.addLineItems(lineItems);
        invoice.addLineItems(lineItems2);

        //then
        assertEquals(lineItems.size(), invoice.getLineItems().size());
    }

    @Test
    public void given_invoice_when_addlineitemtoinvoice_then_expectequalsums(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Invoice invoice = new Invoice(invoiceNumber, bookingId);

        LineItem lineItem = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());

        double expectedSum = lineItem.getPrice() * lineItem.getRoomCount();

        //when
        invoice.addLineItem(lineItem);

        //then
        assertEquals(expectedSum, invoice.getSum());
    }

    @Test
    public void given_invoice_when_addlineitemstoinvoice_then_expectequalsums(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Invoice invoice = new Invoice(invoiceNumber, bookingId);

        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        LineItem lineItem2 = new LineItem(RoomCategory.DOUBLE, 2, RoomCategory.DOUBLE.getPrice());
        lineItems.add(lineItem);
        lineItems.add(lineItem2);

        double expectedSum = 0.0;
        expectedSum += lineItem.getPrice() * lineItem.getRoomCount();
        expectedSum += lineItem2.getPrice() * lineItem2.getRoomCount();

        //when
        invoice.addLineItems(lineItems);

        //then
        assertEquals(expectedSum, invoice.getSum());
    }

        @Test
        public void given_twoidenticallineItems_when_equals_then_expectedtrue(){

        //given
        LineItem lineItem = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        LineItem lineItem2 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());

        //when
        boolean identicalLineItems = lineItem.equals(lineItem2);

        //then
        assertTrue(identicalLineItems);
    }

    @Test
    public void given_invoicewithonelineitem_when_lineitemremoved_then_lineitemsequalemptylist(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());
        LineItem lineItem = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItem);

        //when
        invoice.removeLineItem(lineItem);

        //then
        assertEquals(Collections.emptyList(), invoice.getLineItems());
    }

    @Test
    public void given_invoicewithtwolineitems_when_lineitemremoved_then_lineitemsnotequalsnullorempty(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());

        List<LineItem> lineItems = new ArrayList<>();
        LineItem l1 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        LineItem l2 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        lineItems.add(l1);
        lineItems.add(l2);

        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);

        //when
        invoice.removeLineItem(l1);

        //then
        assertNotNull(invoice.getLineItems());
        assertNotEquals(Collections.emptyList(), invoice.getLineItems());
    }

    @Test
    public void given_invoicewithtwolineitems_when_removealllineitems_then_lineitemsequalemptylist(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());

        List<LineItem> lineItems = new ArrayList<>();
        LineItem l1 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        LineItem l2 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        lineItems.add(l1);
        lineItems.add(l2);
        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);

        //when
        invoice.removeAllLineItems();

        //then
        assertEquals(Collections.emptyList(), invoice.getLineItems());
    }

    @Test
    public void given_invoicewiththreelineitems_when_removelastlineitem_then_lastlineitemremoved(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());

        List<LineItem> lineItems = new ArrayList<>();
        LineItem l1 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        LineItem l2 = new LineItem(RoomCategory.DOUBLE, 2, RoomCategory.DOUBLE.getPrice());
        LineItem l3 = new LineItem(RoomCategory.SUPERIOR, 2, RoomCategory.SUPERIOR.getPrice());
        lineItems.add(l1);
        lineItems.add(l2);
        lineItems.add(l3);
        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);

        //when
        LineItem lastLineItem = invoice.removeLastLineItem();

        //then
        assertEquals(l3, lastLineItem);
    }

    @Test
    public void given_invoicewithzerolineitems_when_removelastlineitem_then_returneditemequalsnull(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());

        List<LineItem> lineItems = new ArrayList<>();
        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);

        //when
        LineItem removedLineItem = invoice.removeLastLineItem();

        //then
        assertNull(removedLineItem);
    }

    @Test
    public void given_invoicewiththreelineitems_when_removefirstlineitem_then_firstlineitemremoved(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());

        List<LineItem> lineItems = new ArrayList<>();
        LineItem l1 = new LineItem(RoomCategory.SINGLE, 2, RoomCategory.SINGLE.getPrice());
        LineItem l2 = new LineItem(RoomCategory.DOUBLE, 2, RoomCategory.DOUBLE.getPrice());
        LineItem l3 = new LineItem(RoomCategory.SUPERIOR, 2, RoomCategory.SUPERIOR.getPrice());
        lineItems.add(l1);
        lineItems.add(l2);
        lineItems.add(l3);
        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);

        //when
        LineItem firstLineItem = invoice.removeFirstLineItem();

        //then
        assertEquals(l1, firstLineItem);
    }

    @Test
    public void given_invoicewithzerolineitems_when_removefirstlineitem_then_returneditemequalsnull(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = new BookingId(UUID.randomUUID());

        List<LineItem> lineItems = new ArrayList<>();
        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);

        //when
        LineItem removedLineItem = invoice.removeFirstLineItem();

        //then
        assertNull(removedLineItem);
    }
}
