package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
public class Room {
    public Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

    @Builder
    public Room(RoomCategory roomCategory, Integer roomNumber, RoomStatus roomStatus, BookingId bookingId) {
        this.roomCategory = roomCategory;
        this.roomNumber = roomNumber;
        this.roomStatus = roomStatus;
        this.bookingId = bookingId;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //    public static Builder builder() {
//        return new Builder();
//    }
//

    public void occupy(BookingId bookingId){
        this.roomStatus = RoomStatus.OCCUPIED;
        this.bookingId = bookingId;
    }

    public  void  checkOut(){
        this.roomStatus = RoomStatus.CLEANING;
        this.bookingId = null;
    }



//    public static class Builder {
//
//        private final Room instance;
//
//        public Builder() {
//            this.instance = new Room();
//        }
//
//        public Builder withRoomCategory(RoomCategory roomCategory) {
//            this.instance.roomCategory = roomCategory;
//            return this;
//        }
//
//        public Builder withRoomNumber(Integer roomNumber) {
//            this.instance.roomNumber = roomNumber;
//            return this;
//        }
//
//        public Builder withRoomStatus(RoomStatus roomStatus) {
//            this.instance.roomStatus = roomStatus;
//            return this;
//        }
//
//        public Builder withBookingId(BookingId bookingId){
//            this.instance.bookingId = bookingId;
//            return this;
//        }
//
//
//        public Room build() {
//  //          Objects.requireNonNull(this.instance.roomId, "type must be set in room");
//            return this.instance;
//        }
//    }
}
