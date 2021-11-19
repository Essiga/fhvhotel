package at.fhv.hotelsoftware.domain.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
@Getter
public class Booking {

    private Long id;
    private BookingId bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private RoomCategory roomCategory;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer luxusRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;
    private CustomerId customerId;
    private List<Room> rooms;

    //private LinkedList<String> extraServices;

    public static Builder builder() {
        return new Builder();
    }

    private Booking() {}


    private void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "customer='" + customerId + '\'' +
                ", fromDate=" + checkInDate +
                ", toDate=" + checkOutDate +
                ", roomCategory=" + roomCategory +
                ", voucherCode=" + voucherCode +
                ", bookingStatus=" + bookingStatus +
                '}';
    }

    //check in liste von rooms dann ids heraus lesen und in booking hinzufügen
    //room status auch hier ändern nicht mit setRoomStatus sonder occupyBooking
    public void checkIn(){
        //roomIds.add(room.getRoomId())
        this.bookingStatus = BookingStatus.CHECKEDIN;
        //TODO: Change RoomStatus
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

        public Builder withCustomer(CustomerId customerId) {
            this.instance.customerId = customerId;
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

        public Builder withRooms(List<Room> rooms) {
            this.instance.rooms = rooms;
            return this;
        }
        //TODO: remove
        public Builder withSingleRoom(Room room) {
            this.instance.rooms = new LinkedList<Room>();
            this.instance.rooms.add(room);
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
