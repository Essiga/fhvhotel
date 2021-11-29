package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTests {

    @Test
    public void given_properties_when_new_invoice_then_reflectsprops(){
        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 2, RoomCategory.DOUBLE.getPrice()));
        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        CustomerData customerData = CustomerData.fromCustomer(customer);

        //when
        Invoice invoice = new Invoice(invoiceNumber, lineItems, customerData);

        //then
        assertEquals(invoiceNumber, invoice.getInvoiceNumber());
        assertEquals(lineItems, invoice.getLineItems());
        assertEquals(customerData, invoice.getCustomerData());
    }

    @Test
    public void given_invoice_when_sum_then_reflectpriceofallrooms() throws InvoiceAlreadyCreatedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().withSingleRoom(1).withDoubleRoom(1).withSuperiorRoom(1).withBookingId(bookingId).build();
        double expectedPrice = RoomCategory.SINGLE.getPrice() + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();
        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");

        Invoice invoice = booking.createInvoice(customer);

        //when
        double sum = invoice.getSum();

        //then
        assertEquals(expectedPrice, sum);
    }

    @Test
    public void given_booking_when_invoicealreadycreated_then_throwinvoicealreadycreatedexception() throws InvoiceAlreadyCreatedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().withSingleRoom(1).withDoubleRoom(1).withSuperiorRoom(1).withBookingId(bookingId).build();
        double expectedPrice = RoomCategory.SINGLE.getPrice() + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();
        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        Invoice invoice = booking.createInvoice(customer);

        //when...then
        assertThrows(InvoiceAlreadyCreatedException.class, () -> booking.createInvoice(customer));
    }



    @Test
    public void given_invoice_when_addlineitemstoinvoice_then_expectequalsize(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 2, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), 3, RoomCategory.SUPERIOR.getPrice()));

        List<LineItem> lineItems2 = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 2, RoomCategory.DOUBLE.getPrice()));

        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        CustomerData customerData = CustomerData.fromCustomer(customer);
        Invoice invoice = new Invoice(invoiceNumber, lineItems, customerData);

        lineItems.addAll(lineItems2);

        //when
        invoice.addLineItems(lineItems2);

        //then
        assertEquals(lineItems.size(), invoice.getLineItems().size());
    }




        @Test
        public void given_twoidenticallineItems_when_equals_then_expectedtrue(){

        //given
        LineItem lineItem = new LineItem(RoomCategory.SINGLE.toString(), 2, RoomCategory.SINGLE.getPrice());
        LineItem lineItem2 = new LineItem(RoomCategory.SINGLE.toString(), 2, RoomCategory.SINGLE.getPrice());

        //when
        boolean identicalLineItems = lineItem.equals(lineItem2);

        //then
        assertTrue(identicalLineItems);
    }

    @Test
    public void given_invoicewithonelineitem_when_lineitemremoved_then_lineitemsequalemptylist(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        CustomerData customerData = CustomerData.fromCustomer(customer);
        Invoice invoice = new Invoice(invoiceNumber, lineItems, customerData);

        //when
        invoice.removeLineItem(lineItems.get(0));


        //then
        assertEquals(Collections.emptyList(), invoice.getLineItems());
    }

    @Test
    public void given_invoicewithtwolineitems_when_lineitemremoved_then_lineitemsnotequalsnullorempty(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, RoomCategory.DOUBLE.getPrice()));
        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        CustomerData customerData = CustomerData.fromCustomer(customer);
        Invoice invoice = new Invoice(invoiceNumber, lineItems, customerData);

        //when
        invoice.removeLineItem(lineItems.get(0));


        //then
        assertEquals(1, invoice.getLineItems().size());
    }

    @Test
    public void given_invoicewithtwolineitems_when_removealllineitems_then_lineitemsequalemptylist(){

        //given
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, RoomCategory.DOUBLE.getPrice()));
        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        CustomerData customerData = CustomerData.fromCustomer(customer);
        Invoice invoice = new Invoice(invoiceNumber, lineItems, customerData);

        //when
        invoice.removeAllLineItems();

        //then
        assertEquals(Collections.emptyList(), invoice.getLineItems());
    }


//    @Test
//    public void given_invoicewiththreelineitems_when_removelastlineitem_then_lastlineitemremoved(){
//
//        //given
//        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
//        BookingId bookingId = new BookingId(UUID.randomUUID());
//
//        List<LineItem> lineItems = new ArrayList<>();
//        LineItem l1 = new LineItem(RoomCategory.SINGLE.toString(), 2, RoomCategory.SINGLE.getPrice());
//        LineItem l2 = new LineItem(RoomCategory.DOUBLE.toString(), 2, RoomCategory.DOUBLE.getPrice());
//        LineItem l3 = new LineItem(RoomCategory.SUPERIOR.toString(), 2, RoomCategory.SUPERIOR.getPrice());
//        lineItems.add(l1);
//        lineItems.add(l2);
//        lineItems.add(l3);
//        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);
//
//        //when
//        LineItem lastLineItem = invoice.removeLastLineItem();
//
//        //then
//        assertEquals(l3, lastLineItem);
//    }
//
//    @Test
//    public void given_invoicewithzerolineitems_when_removelastlineitem_then_returneditemequalsnull(){
//
//        //given
//        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
//        BookingId bookingId = new BookingId(UUID.randomUUID());
//
//        List<LineItem> lineItems = new ArrayList<>();
//        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);
//
//        //when
//        LineItem removedLineItem = invoice.removeLastLineItem();
//
//        //then
//        assertNull(removedLineItem);
//    }
//
//    @Test
//    public void given_invoicewiththreelineitems_when_removefirstlineitem_then_firstlineitemremoved(){
//
//        //given
//        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
//        BookingId bookingId = new BookingId(UUID.randomUUID());
//
//        List<LineItem> lineItems = new ArrayList<>();
//        LineItem l1 = new LineItem(RoomCategory.SINGLE.toString(), 2, RoomCategory.SINGLE.getPrice());
//        LineItem l2 = new LineItem(RoomCategory.DOUBLE.toString(), 2, RoomCategory.DOUBLE.getPrice());
//        LineItem l3 = new LineItem(RoomCategory.SUPERIOR.toString(), 2, RoomCategory.SUPERIOR.getPrice());
//        lineItems.add(l1);
//        lineItems.add(l2);
//        lineItems.add(l3);
//        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);
//
//        //when
//        LineItem firstLineItem = invoice.removeFirstLineItem();
//
//        //then
//        assertEquals(l1, firstLineItem);
//    }
//
//    @Test
//    public void given_invoicewithzerolineitems_when_removefirstlineitem_then_returneditemequalsnull(){
//
//        //given
//        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
//        BookingId bookingId = new BookingId(UUID.randomUUID());
//
//        List<LineItem> lineItems = new ArrayList<>();
//        Invoice invoice = new Invoice(invoiceNumber, bookingId, lineItems);
//
//        //when
//        LineItem removedLineItem = invoice.removeFirstLineItem();
//
//        //then
//        assertNull(removedLineItem);
//    }
}
