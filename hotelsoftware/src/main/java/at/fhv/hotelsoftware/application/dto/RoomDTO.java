package at.fhv.hotelsoftware.application.dto;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data //required by Hibernate
@NoArgsConstructor
public final class RoomDTO {

    private Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

    @Builder
    public RoomDTO(Long id, RoomCategory roomCategory, Integer roomNumber, RoomStatus roomstatus, BookingId bookingId){
        this.id = id;
        this.roomCategory = roomCategory;
        this.roomNumber = roomNumber;
        this.roomStatus = roomstatus;
        this.bookingId = bookingId;
    }

    public static RoomDTO fromRoom(Room room){

        return new RoomDTO(room.getId(),
                           room.getRoomCategory(),
                           room.getRoomNumber(),
                           room.getRoomStatus(),
                           room.getBookingId());
    }

    public static List<RoomDTO> fromRoomList(List<Room> rooms){

        return rooms
                .stream()
                .map(room ->
                        new RoomDTO(
                                room.getId(),
                                room.getRoomCategory(),
                                room.getRoomNumber(),
                                room.getRoomStatus(),
                                room.getBookingId()
                                ))
                .collect(Collectors.toList());
    }
}
