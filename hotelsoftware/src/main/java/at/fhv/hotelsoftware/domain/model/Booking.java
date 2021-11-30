package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
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

    public static Builder builder() {
        return new Builder();
    }

    private Booking() {
    }

    public void setVoucherCode(VoucherCode voucherCode) {
        this.voucherCode = voucherCode;
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public BookingId getBookingId() {
        return this.bookingId;
    }

    public GuestId getGuestId() {
        return guestId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getCancellationDeadLine() {
        return cancellationDeadLine;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public VoucherCode getVoucherCode() {
        return voucherCode;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public Integer getSingleRoom() {
        return singleRoom;
    }

    public Integer getDoubleRoom() {
        return doubleRoom;
    }

    public Integer getSuperiorRoom() {
        return superiorRoom;
    }



    public void checkIn(){
        this.bookingStatus = BookingStatus.CHECKEDIN;
    }

    public void complete(){
        this.bookingStatus = BookingStatus.COMPLETED;
    }

    public Invoice createInvoice(Guest guest) throws InvoiceAlreadyCreatedException {
        if(invoices.isEmpty()) {
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

            CustomerData customerData = CustomerData.fromGuest(guest);

            Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), lineItems, customerData);

            invoices.add(invoice);
            return invoice;
        }
        else{
            throw new InvoiceAlreadyCreatedException("An invoice for this booking has already been created.");
        }
    }

    public static class Builder {

        private final Booking instance;


        public Builder() {
            this.instance = new Booking();
        }

        public Builder withBookingId(BookingId bookingId) {
            this.instance.bookingId = bookingId;
            return this;
        }

        public Builder withLongId(Long id) {
            this.instance.id = id;
            return this;
        }

        public Builder withGuestId(GuestId guestId) {
            this.instance.guestId = guestId;
            return this;
        }

        public Builder withCheckInDate(LocalDate checkInDate) {
            this.instance.checkInDate = checkInDate;
            return this;
        }

        public Builder withCheckOutDate(LocalDate checkOutDate) {
            this.instance.checkOutDate = checkOutDate;
            return this;
        }

        public Builder withCancellationDeadLine(LocalDate date) {
            this.instance.cancellationDeadLine = date;
            return this;
        }

        public Builder withVoucherCode(VoucherCode voucherCode) {
            this.instance.voucherCode = voucherCode;
            return this;
        }

        public Builder withBookingStatus(BookingStatus bookingStatus) {
            this.instance.bookingStatus = bookingStatus;
            return this;
        }

        public Builder withSingleRoom(Integer singleRoom) {
            this.instance.singleRoom = singleRoom;
            return this;
        }

        public Builder withDoubleRoom(Integer doubleRoom) {
            this.instance.doubleRoom = doubleRoom;
            return this;
        }

        public Builder withSuperiorRoom(Integer superiorRoom) {
            this.instance.superiorRoom = superiorRoom;
            return this;
        }

        public Booking build() {
            Objects.requireNonNull(this.instance.bookingId, "type must be set in booking");
            this.instance.invoices = new LinkedList<>();
            return this.instance;
        }
    }
}
