package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.view.annotations.DateNotNullOrEarlierThanToday;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class BookingForm {

    @Min(value = 0, message = "Value cannot be less than 0")
    private Integer singleRoomCount = 0;

    @Min(value = 0, message = "Value cannot be less than 0")
    private Integer doubleRoomCount = 0;

    @Min(value = 0, message = "Value cannot be less than 0")
    private Integer superiorRoomCount = 0;

    @DateNotNullOrEarlierThanToday(message = "Date must be today or in the future")
    private String checkInDate;

    @DateNotNullOrEarlierThanToday(message = "Date must be in the future")
    private String checkOutDate;

    private String voucherCode = " ";

    private BookingId bookingId;

    private List<RoomDTO> roomList;

    private Boolean validDuration = true;

    private Boolean validCategoryCount = true;
}
