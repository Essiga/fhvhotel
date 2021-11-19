package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class BookingDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private BookingId bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private RoomCategory roomCategory;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;
    private List<Room> rooms;

    public BookingDTO(){}

    public BookingDTO(String firstName, String lastName, BookingId bookingId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, RoomCategory roomCategory, VoucherCode voucherCode, BookingStatus bookingStatus, List<Room> rooms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.roomCategory = roomCategory;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
        this.rooms = rooms;
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

    public List<Room> getRooms() {
        return rooms;
    }


    public static BookingDTO fromBooking(Booking booking, Customer customer){
        return new BookingDTO(customer.getFirstName(), customer.getLastName(), booking.getBookingId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getCancellationDeadLine(),
                booking.getRoomCategory(),
                booking.getVoucherCode(),
                booking.getBookingStatus(),
                booking.getRooms());
    }

    public static List<BookingDTO> fromBookingList(List<Booking> bookings, List<Customer> customers){
       List<BookingDTO> bookingDTOs = new LinkedList<BookingDTO>();

        for (Booking booking:bookings) {
            for (Customer customer:customers) {
                if(booking.getCustomerId() == customer.getCustomerId()){
                    bookingDTOs.add(BookingDTO.fromBooking(booking, customer));
                }
            }
        }
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

        public BookingDTO.Builder withRoomIds(List<Room> rooms){
            this.instance.rooms = rooms;
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
