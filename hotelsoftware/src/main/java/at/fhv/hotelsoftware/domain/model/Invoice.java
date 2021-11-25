package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Invoice {

    private Long id;
    private InvoiceNumber invoiceNumber;
    private BookingId bookingId;
    private List<LineItem> lineItems;

    public Invoice(BookingId bookingId) {
        this.invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        this.bookingId = bookingId;
    }

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;
    }

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId, LineItem lineItem) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;

        this.lineItems = new ArrayList<>();
        lineItems.add(lineItem);
    }

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId, List<LineItem> lineItems) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;
        this.lineItems = lineItems;
    }

    public static Invoice createInvoiceFromBooking(Booking booking){
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = booking.getBookingId();

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE, booking.getSingleRoom(), RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE, booking.getSingleRoom(), RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR, booking.getSingleRoom(), RoomCategory.SUPERIOR.getPrice()));

        return new Invoice(invoiceNumber, bookingId, lineItems);
    }

    public void addLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem lineItem) {

        if (lineItems == null){
            lineItems = new ArrayList<>();
        }

        lineItems.add(lineItem);
    }

    public void removeAllLineItems() {
        this.lineItems = null;
    }

    public void removeLineItem(LineItem lineItem) {

        if (lineItem == null || lineItems == null)
            return;

        for (LineItem l : lineItems){

            if (l.equals(lineItem)){
                lineItems.remove(l);
                break;
            }
        }

        if (lineItems.isEmpty())
            lineItems = null;
    }

    public double getSum(Booking booking){
        double sum = 0.0;

        sum += RoomCategory.SINGLE.getPrice() * booking.getSingleRoom();
        sum += RoomCategory.DOUBLE.getPrice() * booking.getDoubleRoom();
        sum += RoomCategory.SUPERIOR.getPrice() * booking.getSuperiorRoom();

        return sum;
    }

    public double getSum(){

        if (lineItems == null)
            return 0.0;

        Double sum = 0.0;

        for (LineItem lineItem : lineItems){
            Integer count = lineItem.getRoomCount();
            double price = lineItem.getPrice();
            sum += price * count;
        }

        return sum;
    }
}
