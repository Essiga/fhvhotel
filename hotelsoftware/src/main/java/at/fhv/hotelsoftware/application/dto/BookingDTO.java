package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class BookingDTO {

    private Long id;
    private BookingId bookingId;
    private String customer;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private RoomCategory roomCategory;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer luxusRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;
    private CustomerId customerId;


    public BookingDTO(){}

    public BookingDTO(BookingId bookingId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, RoomCategory roomCategory, Integer singleRoom, Integer doubleRoom, Integer luxusRoom, VoucherCode voucherCode, BookingStatus bookingStatus, CustomerId customerId, List<RoomId> roomIds) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.roomCategory = roomCategory;
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
        this.luxusRoom = luxusRoom;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
        this.customerId = customerId;
    }



    public Long getId() {
        return id;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDate getCancellationDeadLine() {
        return cancellationDeadLine;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public VoucherCode getVoucherCode() {
        return voucherCode;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }


    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }


    public static BookingDTO fromBooking(Booking booking){
        return new BookingDTO(booking.getBookingId(),
                booking.getCustomer(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getCancellationDeadLine(),
                booking.getRoomCategory(),
                booking.getVoucherCode(),
                booking.getBookingStatus(),
                booking.getRooms());
    }

    public static List<BookingDTO> fromBookingList(List<Booking> booking){
        return booking
                .stream()
                .map(bookings ->
                        new BookingDTO(bookings.getBookingId(),
                                bookings.getCustomer(),
                                bookings.getCheckInDate(),
                                bookings.getCheckOutDate(),
                                bookings.getCancellationDeadLine(),
                                bookings.getRoomCategory(),
                                bookings.getVoucherCode(),
                                bookings.getBookingStatus(),
                                bookings.getRooms()))
                .collect(Collectors.toList());
    }

    public static class Builder{

        private final BookingDTO instance;

        public Builder() {
            this.instance = new BookingDTO();
        }
        public BookingDTO.Builder withId(Long id){
            this.instance.id = id;
            return this;
        }

        public BookingDTO.Builder withCustomer(String customer){
            this.instance.customer = customer;
            return this;
        }
        public BookingDTO.Builder withCheckInDate(LocalDate fromDate){
            this.instance.checkInDate = fromDate;
            return this;
        }
        public BookingDTO.Builder withCheckOutDate(LocalDate toDate){
            this.instance.checkOutDate = toDate;
            return this;
        }
        public BookingDTO.Builder withRoomCategory(RoomCategory roomCategory){
            this.instance.roomCategory = roomCategory;
            return this;
        }

        public BookingDTO.Builder withCancellationDeadline(LocalDate cancellationDeadLine){
            this.instance.cancellationDeadLine = cancellationDeadLine;
            return this;
        }
        public BookingDTO.Builder withVoucherCode(VoucherCode voucherCode){
            this.instance.voucherCode = voucherCode;
            return this;
        }


        /*public BookingDTO.Builder withBookingStatus(BookingStatus bookingStatus){
            this.instance.bookingStatus = bookingStatus;
            return this;
        }*/

        public BookingDTO build(){
            Objects.requireNonNull(this.instance.id, "type must be set in booking");
            return this.instance;
        }

    }
}
