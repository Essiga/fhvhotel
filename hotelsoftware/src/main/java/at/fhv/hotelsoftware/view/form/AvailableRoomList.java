package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.RoomDTO;

import java.util.List;

public class AvailableRoomList {

    private List<RoomDTO> roomDTOs;

    public AvailableRoomList(List<RoomDTO> roomDTOs) {
        this.roomDTOs = roomDTOs;
    }

    public List<RoomDTO> getRoomDTOs() {
        return roomDTOs;
    }

    public void setRoomDTOs(List<RoomDTO> roomDTOs) {
        this.roomDTOs = roomDTOs;
    }
}
