package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.application.dto.BookingDataDTO;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.view.annotations.DateNotNullOrEarlierThanToday;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

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


    public static BookingForm bookingFormFromBookingData(BookingDataDTO bookingData) {

        BookingForm bookingForm = new BookingForm();

        bookingForm.setVoucherCode(bookingData.getVoucher());
        bookingForm.setCheckInDate(bookingData.getCheckInDate());
        bookingForm.setCheckOutDate(bookingData.getCheckOutDate());
        bookingForm.setSingleRoomCount(bookingData.getSingleRoomCount());
        bookingForm.setDoubleRoomCount(bookingData.getDoubleRoomCount());
        bookingForm.setSuperiorRoomCount(bookingData.getSuperiorRoomCount());

        return bookingForm;
    }
}
