package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.RoomDTO;

import java.util.List;

public class FreeRoomListWrapper {

    private List<RoomDTO> freeRoomList;

    public FreeRoomListWrapper(List<RoomDTO> freeRoomList) {
        this.freeRoomList = freeRoomList;
    }

    public List<RoomDTO> getFreeRoomList() {
        return freeRoomList;
    }

    public void setFreeRoomList(List<RoomDTO> freeRoomList) {
        this.freeRoomList = freeRoomList;
    }
}
