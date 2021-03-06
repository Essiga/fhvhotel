package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class BookingDataDTO {

    private GuestId guestId;
    private Integer singleRoomCount;
    private Integer doubleRoomCount;
    private Integer superiorRoomCount;
    private String checkInDate;
    private String checkOutDate;

}
