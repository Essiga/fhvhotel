package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.RoomDTO;

import java.util.ArrayList;
import java.util.List;

public class AvailableRoomList {

    private ArrayList<RoomDTO> roomDTOs;

    public AvailableRoomList(ArrayList<RoomDTO> roomDTOs) {
        this.roomDTOs = roomDTOs;
    }

    public ArrayList<RoomDTO> getRoomDTOs() {
        return roomDTOs;
    }

    public void setRoomDTOs(ArrayList<RoomDTO> roomDTOs) {
        this.roomDTOs = roomDTOs;
    }
}
