package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Invoice {
    private Long id;
    private InvoiceNumber invoiceNumber;
    private BookingId bookingId;

    public Invoice(InvoiceNumber invoiceNumber, BookingId bookingId) {
        this.invoiceNumber = invoiceNumber;
        this.bookingId = bookingId;
    }

    public double getSum(Booking booking){
        Double sum = 0.0;

        sum += booking.getSingleRoom() * RoomCategory.SINGLE.getPrice();
        sum += booking.getDoubleRoom() * RoomCategory.DOUBLE.getPrice();
        sum += booking.getSuperiorRoom() * RoomCategory.SUPERIOR.getPrice();

        return sum;
    }
}
