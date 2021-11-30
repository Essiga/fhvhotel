package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import lombok.Data;

import java.util.List;

@Data
public class RoomForm {

    public Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

    private List<RoomDTO> roomList;

    public void setRoomList(List<RoomDTO> roomList) {
        this.roomList = roomList;
    }

    public List<RoomDTO> getRoomList() {
        return roomList;
    }


}
