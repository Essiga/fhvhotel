package at.fhv.hotelsoftware.domain;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class Booking {

    private Long id;
    private Id bookingId;
    private String customer;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private LocalDateTime cancellationDeadLine;
    private RoomCategory roomCategory;
    private Integer roomCount;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;

    //private LinkedList<String> extraServices;

    public static Builder builder() {
        return new Builder();
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Id getBookingId() {
        return bookingId;
    }

    public void setBookingId(Id bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public LocalDateTime getCancellationDeadLine() {
        return cancellationDeadLine;
    }

    public void setCancellationDeadLine(LocalDateTime cancellationDeadLine) {
        this.cancellationDeadLine = cancellationDeadLine;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public VoucherCode getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(VoucherCode voucherCode) {
        this.voucherCode = voucherCode;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customer='" + customer + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", roomCategory=" + roomCategory +
                ", roomCount=" + roomCount +
                ", voucherCode=" + voucherCode +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

    public static class Builder {

        private final Booking instance;


        public Builder() {
            this.instance = new Booking();
        }

        public Builder withId(Id id) {
            this.instance.bookingId = id;
            return this;
        }

        public Builder withLongId(Long id) {
            this.instance.id = id;
            return this;
        }

        public Builder withCustomer(String customer) {
            this.instance.customer = customer;
            return this;
        }

        public Builder withFromDate(LocalDateTime fromDate) {
            this.instance.fromDate = fromDate;
            return this;
        }

        public Builder withToDate(LocalDateTime toDate) {
            this.instance.toDate = toDate;
            return this;
        }

        public Builder withCancellationDeadLine(LocalDateTime date) {
            this.instance.cancellationDeadLine = date;
            return this;
        }

        public Builder withRoomCategory(RoomCategory roomCategory) {
            this.instance.roomCategory = roomCategory;
            return this;
        }

        public Builder withRoomCount(Integer roomCount) {
            this.instance.roomCount = roomCount;
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


        /* public Builder withExtraServices(LinkedList<String> extraServices){
            this.instance.extraServices = extraServices;
            return this;
        } */


        public Booking build() {
            Objects.requireNonNull(this.instance.bookingId, "type must be set in booking");
            return this.instance;
        }
    }
}
