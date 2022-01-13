package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.GuestDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.view.annotations.DateNotNullOrEarlierThanToday;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
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

    @Builder
    public BookingForm(Integer singleRoomCount, Integer doubleRoomCount, Integer superiorRoomCount, String checkInDate, String checkOutDate) {
        this.singleRoomCount = singleRoomCount;
        this.doubleRoomCount = doubleRoomCount;
        this.superiorRoomCount = superiorRoomCount;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public static BookingForm fromBookingDataDTO (BookingDataDTO bookingDataDTO) {
        return BookingForm.builder()
                .singleRoomCount(bookingDataDTO.getSingleRoomCount())
                .doubleRoomCount(bookingDataDTO.getDoubleRoomCount())
                .superiorRoomCount(bookingDataDTO.getSuperiorRoomCount())
                .checkInDate(bookingDataDTO.getCheckInDate())
                .checkOutDate(bookingDataDTO.getCheckOutDate())
                .build();
    }
}
