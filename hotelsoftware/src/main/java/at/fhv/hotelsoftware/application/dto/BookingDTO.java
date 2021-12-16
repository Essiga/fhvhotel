package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingStatus;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.VoucherCode;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public final class BookingDTO {

    private BookingId bookingId;
    private GuestId guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer superiorRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;

    @Builder
    public BookingDTO(BookingId bookingId, GuestId guestId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, Integer singleRoom, Integer doubleRoom, Integer superiorRoom, VoucherCode voucherCode, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
        this.superiorRoom = superiorRoom;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
    }

    public static BookingDTO fromBooking(Booking booking) {

        return new BookingDTO(booking.getBookingId(),
                booking.getGuestId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getCancellationDeadLine(),
                booking.getSingleRoom(),
                booking.getDoubleRoom(),
                booking.getSuperiorRoom(),
                booking.getVoucherCode(),
                booking.getBookingStatus());
    }

    public static List<BookingDTO> fromBookingList(List<Booking> bookings) {

        return bookings
                .stream()
                .map(booking ->
                        new BookingDTO(booking.getBookingId(),
                                booking.getGuestId(),
                                booking.getCheckInDate(),
                                booking.getCheckOutDate(),
                                booking.getCancellationDeadLine(),
                                booking.getSingleRoom(),
                                booking.getDoubleRoom(),
                                booking.getSuperiorRoom(),
                                booking.getVoucherCode(),
                                booking.getBookingStatus()))
                .collect(Collectors.toList());
    }
}
