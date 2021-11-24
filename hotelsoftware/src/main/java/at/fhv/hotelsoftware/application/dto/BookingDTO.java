package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public final class BookingDTO {

    private Long id;
    private BookingId bookingId;
    private CustomerId customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer luxusRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;

    @Builder
    public BookingDTO(BookingId bookingId, CustomerId customerId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, Integer singleRoom, Integer doubleRoom, Integer luxusRoom, VoucherCode voucherCode, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
        this.luxusRoom = luxusRoom;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
    }

//    public void setBookingId(BookingId bookingId) {
//        this.bookingId = bookingId;
//    }


    public static BookingDTO fromBooking(Booking booking){
        return new BookingDTO(booking.getBookingId(),
                booking.getCustomerId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getCancellationDeadLine(),
                booking.getSingleRoom(),
                booking.getDoubleRoom(),
                booking.getLuxusRoom(),
                booking.getVoucherCode(),
                booking.getBookingStatus());
    }

    public static List<BookingDTO> fromBookingList(List<Booking> booking){
        return booking
                .stream()
                .map(bookings ->
                        new BookingDTO(bookings.getBookingId(),
                                bookings.getCustomerId(),
                                bookings.getCheckInDate(),
                                bookings.getCheckOutDate(),
                                bookings.getCancellationDeadLine(),
                                bookings.getSingleRoom(),
                                bookings.getDoubleRoom(),
                                bookings.getLuxusRoom(),
                                bookings.getVoucherCode(),
                                bookings.getBookingStatus()))
                .collect(Collectors.toList());
    }

}
