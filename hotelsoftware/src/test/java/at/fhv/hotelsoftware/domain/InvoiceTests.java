package at.fhv.hotelsoftware.domain;

import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
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
        Guest guest = new Guest(new GuestId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        GuestData guestData = GuestData.fromGuest(guest);

        //when
        Invoice invoice = new Invoice(invoiceNumber, lineItems, guestData);

        //then
        assertEquals(invoiceNumber, invoice.getInvoiceNumber());
        assertEquals(lineItems, invoice.getLineItems());
        assertEquals(guestData, invoice.getGuestData());
    }

    @Test
    public void given_invoice_when_sum_then_reflectpriceofallrooms() throws InvoiceAlreadyCreatedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().singleRoom(1).doubleRoom(1).superiorRoom(1).bookingId(bookingId).build();
        double expectedPrice = RoomCategory.SINGLE.getPrice() + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();
        Guest guest = new Guest(new GuestId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");

        Invoice invoice = booking.createInvoice(guest);

        //when
        double sum = invoice.getSum();

        //then
        assertEquals(expectedPrice, sum);
    }

    @Test
    public void given_booking_when_invoicealreadycreated_then_throwinvoicealreadycreatedexception() throws InvoiceAlreadyCreatedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().singleRoom(1).doubleRoom(1).superiorRoom(1).bookingId(bookingId).build();
        double expectedPrice = RoomCategory.SINGLE.getPrice() + RoomCategory.DOUBLE.getPrice() + RoomCategory.SUPERIOR.getPrice();
        Guest guest = new Guest(new GuestId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        Invoice invoice = booking.createInvoice(guest);

        //when...then
        assertThrows(InvoiceAlreadyCreatedException.class, () -> booking.createInvoice(guest));
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
}
