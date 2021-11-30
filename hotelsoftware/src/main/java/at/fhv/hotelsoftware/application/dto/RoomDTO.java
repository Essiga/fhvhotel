package at.fhv.hotelsoftware.application.dto;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public final class RoomDTO {

    public Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

    public RoomDTO(){}

    public RoomDTO(Long id,
                   RoomCategory roomCategory,
                   Integer roomNumber,
                   RoomStatus roomstatus,
                   BookingId bookingId){

        this.id = id;
        this.roomCategory = roomCategory;
        this.roomNumber = roomNumber;
        this.roomStatus = roomstatus;
        this.bookingId = bookingId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public static RoomDTO fromRoom(Room room){
        return new RoomDTO(room.getId(),
                           room.getRoomCategory(),
                           room.getRoomNumber(),
                           room.getRoomStatus(),
                           room.getBookingId());
    }

    public static List<RoomDTO> fromRoomList(List<Room> allRoom){
        return allRoom
                .stream()
                .map(allRooms ->
                        new RoomDTO(
                                allRooms.getId(),
                                allRooms.getRoomCategory(),
                                allRooms.getRoomNumber(),
                                allRooms.getRoomStatus(),
                                allRooms.getBookingId()
                                ))
                .collect(Collectors.toList());
    }

    public static class Builder {

        private final RoomDTO instance;

        public Builder() {
            this.instance = new RoomDTO();
        }

        public RoomDTO.Builder withid(Long id) {
            this.instance.id = id;
            return this;
        }

        public RoomDTO.Builder withRoomCategory(RoomCategory roomCategory) {
            this.instance.roomCategory = roomCategory;
            return this;
        }

        public RoomDTO.Builder withRoomNumber(Integer roomNumber) {
            this.instance.roomNumber = roomNumber;
            return this;
        }

        public RoomDTO.Builder withRoomStatus(RoomStatus roomStatus) {
            this.instance.roomStatus = roomStatus;
            return this;
        }

        public RoomDTO.Builder withBookingId(BookingId bookingId) {
            this.instance.bookingId = bookingId;
            return this;
        }

        public RoomDTO build() {
            Objects.requireNonNull(this.instance.id, "type must be set in room");
            return this.instance;
        }
    }
}
