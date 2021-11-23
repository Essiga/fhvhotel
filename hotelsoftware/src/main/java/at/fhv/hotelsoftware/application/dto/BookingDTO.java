package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class BookingDTO {

    private Long id;
    private BookingId bookingId;
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
    private List<RoomId> roomIds;


    public BookingDTO(){}

    public BookingDTO(BookingId bookingId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, RoomCategory roomCategory, Integer singleRoom, Integer doubleRoom, Integer luxusRoom, VoucherCode voucherCode, BookingStatus bookingStatus, CustomerId customerId, List<RoomId> roomIds) {
        this.bookingId = bookingId;
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
        this.roomIds = roomIds;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setCancellationDeadLine(LocalDate cancellationDeadLine) {
        this.cancellationDeadLine = cancellationDeadLine;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getSingleRoom() {
        return singleRoom;
    }

    public void setSingleRoom(Integer singleRoom) {
        this.singleRoom = singleRoom;
    }

    public Integer getDoubleRoom() {
        return doubleRoom;
    }

    public void setDoubleRoom(Integer doubleRoom) {
        this.doubleRoom = doubleRoom;
    }

    public Integer getLuxusRoom() {
        return luxusRoom;
    }

    public void setLuxusRoom(Integer luxusRoom) {
        this.luxusRoom = luxusRoom;
    }

    public void setVoucherCode(VoucherCode voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }

    public List<RoomId> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<RoomId> roomIds) {
        this.roomIds = roomIds;
    }

    public static BookingDTO fromBooking(Booking booking){


        return new BookingDTO(booking.getBookingId(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getCancellationDeadLine(), booking.getRoomCategory(), booking.getSingleRoom(), booking.getDoubleRoom(), booking.getLuxusRoom(), booking.getVoucherCode(), booking.getBookingStatus(), booking.getCustomerId(), booking.getRoomIds())
    }

    public static List<BookingDTO> fromBookingList(List<Booking> bookings){
       List<BookingDTO> bookingDTOs = new LinkedList<BookingDTO>();



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
