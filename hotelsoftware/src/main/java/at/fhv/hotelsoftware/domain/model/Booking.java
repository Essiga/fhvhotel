package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

@Getter
@NoArgsConstructor
public class Booking {

    private Long id;
    private BookingId bookingId;
    private GuestId guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer superiorRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;
    private List<Invoice> invoices;

    @Builder
    public Booking(BookingId bookingId, GuestId guestId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, Integer singleRoom, Integer doubleRoom, Integer superiorRoom, VoucherCode voucherCode, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
        this.superiorRoom = superiorRoom;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
        this.invoices = new ArrayList<>();
    }

    public void checkIn(){
        this.bookingStatus = BookingStatus.CHECKEDIN;
    }

    public void complete(){
        this.bookingStatus = BookingStatus.COMPLETED;
    }

    public Invoice createInvoice(Guest guest) throws InvoiceAlreadyCreatedException {

        if (invoices.isEmpty()) {
            List<LineItem> lineItems = new ArrayList<>();

            if (singleRoom > 0) {
                lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), singleRoom, RoomCategory.SINGLE.getPrice()));
            }

            if (doubleRoom > 0) {
                lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), doubleRoom, RoomCategory.DOUBLE.getPrice()));
            }

            if (superiorRoom > 0) {
                lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), superiorRoom, RoomCategory.SUPERIOR.getPrice()));
            }

            GuestData guestData = GuestData.fromGuest(guest);
            Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), lineItems, guestData);
            invoices.add(invoice);

            return invoice;
        }
        else {
            throw new InvoiceAlreadyCreatedException("An invoice for this booking has already been created.");
        }
    }
}
