package at.fhv.hotelsoftware.domain.model;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Invoice {

    private Long id;
    private InvoiceNumber invoiceNumber;
    private BookingId bookingId;
    private LocalDate invoiceDate;
    private InvoiceStatus invoiceStatus;
    private List<LineItem> lineItems;


    public Invoice(BookingId bookingId) {
        this.invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        this.bookingId = bookingId;
        this.invoiceStatus = InvoiceStatus.OPEN;
        this.invoiceDate = LocalDate.now();
    }

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;
        this.invoiceStatus = InvoiceStatus.OPEN;
        this.invoiceDate = LocalDate.now();
    }

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId, LineItem lineItem) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;
        this.invoiceStatus = InvoiceStatus.OPEN;
        this.invoiceDate = LocalDate.now();

        this.lineItems = new ArrayList<>();
        lineItems.add(lineItem);
    }

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId, List<LineItem> lineItems) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;
        this.lineItems = lineItems;
        this.invoiceStatus = InvoiceStatus.OPEN;
        this.invoiceDate = LocalDate.now();
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    public static Invoice createInvoiceFromBooking(Booking booking){
        InvoiceNumber invoiceNumber = new InvoiceNumber(UUID.randomUUID());
        BookingId bookingId = booking.getBookingId();

        List<LineItem> lineItems = new ArrayList<>();
        Integer singleRooms = booking.getSingleRoom();
        Integer doubleRooms = booking.getDoubleRoom();
        Integer superiorRooms = booking.getSuperiorRoom();

        if (singleRooms > 0) {
            lineItems.add(new LineItem(RoomCategory.SINGLE, singleRooms, RoomCategory.SINGLE.getPrice()));
        }

        if (doubleRooms > 0) {
            lineItems.add(new LineItem(RoomCategory.DOUBLE, doubleRooms, RoomCategory.DOUBLE.getPrice()));
        }

        if (superiorRooms > 0) {
            lineItems.add(new LineItem(RoomCategory.SUPERIOR, superiorRooms, RoomCategory.SUPERIOR.getPrice()));
        }

        return new Invoice(invoiceNumber, bookingId, lineItems);
    }

    public void addLineItems(List<LineItem> lineItems) {

        if (lineItems == null)
            return;

        if (this.lineItems == null) {
            this.lineItems = new ArrayList<>();
        }

        this.lineItems.addAll(lineItems);
    }

    public void addLineItem(LineItem lineItem) {

        if (lineItem == null)
            return;

        if (lineItems == null){
            lineItems = new ArrayList<>();
        }

        lineItems.add(lineItem);
    }

    public void removeAllLineItems() {
        this.lineItems.clear();
    }

    public void removeLineItem(LineItem lineItem) {

        if (lineItem == null || lineItems == null || lineItems.isEmpty())
            return;

        for (LineItem l : lineItems) {

            if (l.equals(lineItem)) {
                lineItems.remove(l);
                break;
            }
        }
    }

    public LineItem removeLastLineItem() {
        if (lineItems == null || lineItems.isEmpty())
            return null;

        int idxOfLastLineItem = lineItems.size()-1;
        LineItem lastLineItem = lineItems.get(idxOfLastLineItem);
        lineItems.remove(lastLineItem);

        return lastLineItem;
    }

    public LineItem removeFirstLineItem() {
        if (lineItems == null || lineItems.isEmpty())
            return null;

        LineItem firstLineItem = lineItems.get(0);
        lineItems.remove(firstLineItem);

        return firstLineItem;
    }

    public double getSum(Booking booking){
        double sum = 0.0;

        sum += RoomCategory.SINGLE.getPrice() * booking.getSingleRoom();
        sum += RoomCategory.DOUBLE.getPrice() * booking.getDoubleRoom();
        sum += RoomCategory.SUPERIOR.getPrice() * booking.getSuperiorRoom();

        return sum;
    }

    public double getSum(){

        if (lineItems == null || lineItems.isEmpty())
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
