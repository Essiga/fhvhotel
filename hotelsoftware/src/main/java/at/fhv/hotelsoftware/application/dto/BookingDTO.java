package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.RoomCategory;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

public final class BookingDTO {
    private String id;
    private String customer;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RoomCategory roomCategory;
    private Integer roomCount;
    private LocalDateTime cancellationDeadline;
    private String voucherCode;
    private LinkedList<String> extraServices;

    public static class Builder{

        private final BookingDTO instance;


        public Builder() {
            this.instance = new BookingDTO();
        }
        public BookingDTO.Builder withId(String id){
            this.instance.id = id;
            return this;
        }
        public BookingDTO.Builder withCustomer(String customer){
            this.instance.customer = customer;
            return this;
        }
        public BookingDTO.Builder withCheckInDate(LocalDateTime checkInDate){
            this.instance.checkInDate = checkInDate;
            return this;
        }
        public BookingDTO.Builder withCheckOutDate(LocalDateTime checkOutDate){
            this.instance.checkOutDate = checkOutDate;
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
        public BookingDTO.Builder withCancellationDeadline(LocalDateTime cancellationDeadline){
            this.instance.cancellationDeadline = cancellationDeadline;
            return this;
        }
        public BookingDTO.Builder withVoucherCode(String voucherCode){
            this.instance.voucherCode = voucherCode;
            return this;
        }
        public BookingDTO.Builder withExtraServices(LinkedList<String> extraServices){
            this.instance.extraServices = extraServices;
            return this;
        }
        public BookingDTO build(){
            Objects.requireNonNull(this.instance.id, "type must be set in booking");
            return this.instance;
        }
    }
}
