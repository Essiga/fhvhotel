package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;

public final class BookingDTO {

    private Long id;
    private Id bookingId;
    private String customer;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate cancellationDeadLine;
    private RoomCategory roomCategory;
    private Integer roomCount;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;

    public BookingDTO(){}

    public BookingDTO(Id bookingId,
                      String customer,
                      LocalDate fromDate,
                      LocalDate toDate,
                      LocalDate cancellationDeadLine,
                      RoomCategory roomCategory,
                      Integer roomCount,
                      VoucherCode voucherCode,
                      BookingStatus bookingStatus) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.roomCategory = roomCategory;
        this.roomCount = roomCount;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
    }

    public Long getId() {
        return id;
    }

    public Id getBookingId() {
        return bookingId;
    }

    public String getCustomer() {
        return customer;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public LocalDate getCancellationDeadLine() {
        return cancellationDeadLine;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public VoucherCode getVoucherCode() {
        return voucherCode;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
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
            this.instance.fromDate = fromDate;
            return this;
        }
        public BookingDTO.Builder withCheckOutDate(LocalDate toDate){
            this.instance.toDate = toDate;
            return this;
        }
        public BookingDTO.Builder withRoomCategory(RoomCategory roomCategory){
            this.instance.roomCategory = roomCategory;
            return this;
        }
        public BookingDTO.Builder withRoomCount(Integer roomCount){
            this.instance.roomCount = roomCount;
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
        public BookingDTO build(){
            Objects.requireNonNull(this.instance.id, "type must be set in booking");
            return this.instance;
        }
    }
}
