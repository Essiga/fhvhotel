package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.view.annotations.DateNotNullOrEarlierThanToday;
import lombok.*;

import javax.validation.constraints.*;

@Data
public class BookingForm {

    @Min(value = 0, message = "Value cannot be less than 0")
    private Integer singleRoomCount = 0;

    @Min(value = 0, message = "Value cannot be less than 0")
    private Integer doubleRoomCount = 0;

    @Min(value = 0, message = "Value cannot be less than 0")
    private Integer luxusRoomCount = 0;

    @DateNotNullOrEarlierThanToday(message = "Date must be today or in the future")
    private String checkInDate;

    @DateNotNullOrEarlierThanToday(message = "Date must be today or in the future")
    private String checkOutDate;


}
