package at.fhv.hotelsoftware.domain.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
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

    public String getCustomer() {
        return customer;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public LocalDate getCancellationDeadLine() {
        return cancellationDeadLine;
    }


    public RoomCategory getRoomCategory() {
        return roomCategory;
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

    public Integer getLuxusRoom() {
        return luxusRoom;
    }

    public List<RoomId> getRoomIds() {
        return roomIds;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customer='" + customer + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", roomCategory=" + roomCategory +
                ", voucherCode=" + voucherCode +
                ", bookingStatus=" + bookingStatus +
                '}';
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
        //TODO: remove
        public Builder withSingleRoomId(RoomId roomIds) {
            this.instance.roomIds = new LinkedList<RoomId>();
            this.instance.roomIds.add(roomIds);
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
