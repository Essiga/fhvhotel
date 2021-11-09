package at.fhv.hotelsoftware.domain.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class Booking {

    private Long id;
    private BookingId bookingId;
    private String customer;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate cancellationDeadLine;
    private RoomCategory roomCategory;
    private Integer roomCount;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer luxusRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;
    private List<RoomId> roomIds;

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

    public BookingId getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getCancellationDeadLine() {
        return cancellationDeadLine;
    }

    public void setCancellationDeadLine(LocalDate cancellationDeadLine) {
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

    public Integer getSingleRoom() {
        return singleRoom;
    }

    public void setSingleRoom(Integer singleRoom) {
        this.singleRoom = singleRoom;
    }

    public Integer getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(Integer doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public Integer getLuxusRoom() {
        return luxusRoom;
    }

    public void setLuxusRoom(Integer luxusRoom) {
        this.luxusRoom = luxusRoom;
    }

    public List<RoomId> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<RoomId> roomIds) {
        this.roomIds = roomIds;
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

        public Builder withId(BookingId bookingId) {
            this.instance.bookingId = bookingId;
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

        public Builder withFromDate(LocalDate fromDate) {
            this.instance.fromDate = fromDate;
            return this;
        }

        public Builder withToDate(LocalDate toDate) {
            this.instance.toDate = toDate;
            return this;
        }

        public Builder withCancellationDeadLine(LocalDate date) {
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

        public Builder withSingleRoom(Integer singleRoom) {
            this.instance.singleRoom = singleRoom;
            return this;
        }

        public Builder withDoubleRoom(Integer doubleRoom) {
            this.instance.doubleRoom = doubleRoom;
            return this;
        }

        public Builder withLuxusRoom(Integer luxusRoom) {
            this.instance.luxusRoom = luxusRoom;
            return this;
        }

        public Builder withRoomIds(List<RoomId> roomIds) {
            this.instance.roomIds = roomIds;
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
