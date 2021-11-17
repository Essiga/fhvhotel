package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class BookingDTO {

    private Long id;
    private String fname;
    private String lname;
    private BookingId bookingId;
    private String customer;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private RoomCategory roomCategory;
    private VoucherCode voucherCode;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer luxusRoom;
    private BookingStatus bookingStatus;
    private List<Room> rooms;
    private String phoneNumber;
    private String email;
    private String country;
    private String city;
    private String streetAdr;
    private String zip;

    public BookingDTO(){}

    public BookingDTO(BookingId bookingId,
                      String customer,
                      LocalDate checkInDate,
                      LocalDate checkOutDate,
                      LocalDate cancellationDeadLine,
                      RoomCategory roomCategory,
                      Integer singleRoom,
                      Integer doubleRoom,
                      Integer luxusRoom,
                      VoucherCode voucherCode,
                      BookingStatus bookingStatus,
                      List<Room> rooms) {

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
        this.rooms = rooms;
    }



    public Long getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAdr() {
        return streetAdr;
    }

    public void setStreetAdr(String streetAdr) {
        this.streetAdr = streetAdr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookingId(BookingId bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public void setVoucherCode(VoucherCode voucherCode) {
        this.voucherCode = voucherCode;
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

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public BookingId getBookingId() {
        return bookingId;
    }

    public String getCustomer() {
        return customer;
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


    public static BookingDTO fromBooking(Booking booking){
        return new BookingDTO(booking.getBookingId(),
                booking.getCustomer(),
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                booking.getCancellationDeadLine(),
                booking.getRoomCategory(),
                booking.getSingleRoom(),
                booking.getDoubleRoom(),
                booking.getLuxusRoom(),
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
                                bookings.getSingleRoom(),
                                bookings.getDoubleRoom(),
                                bookings.getLuxusRoom(),
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
