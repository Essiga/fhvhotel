package at.fhv.hotelsoftware.application.dto;

import lombok.Data;

@Data
public class RoomPriceDTO {

    private double singleRoomPrice;
    private double doubleRoomPrice;
    private double superiorRoomPrice;

    public RoomPriceDTO(double singleRoomPrice, double doubleRoomPrice, double superiorRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
        this.doubleRoomPrice = doubleRoomPrice;
        this.superiorRoomPrice = superiorRoomPrice;
    }
}
