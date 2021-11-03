package at.fhv.hotelsoftware.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

@Component
public class Booking {
    private String id;
    private String customer;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private RoomCategory roomCategory;
    private Integer roomCount;
    private LocalDateTime cancellationDeadline;
    private String voucherCode;
    private LinkedList<String> extraServices;

    public static Builder builder(){return new Builder();}

    public Booking(){

    }
    public static class Builder{

        private final Booking instance;


        public Builder() {
            this.instance = new Booking();
        }
        public Builder withId(String id){
            this.instance.id = id;
            return this;
        }
        public Builder withCustomer(String customer){
            this.instance.customer = customer;
            return this;
        }
        public Builder withCheckInDate(LocalDateTime checkInDate){
            this.instance.checkInDate = checkInDate;
            return this;
        }
        public Builder withCheckOutDate(LocalDateTime checkOutDate){
            this.instance.checkOutDate = checkOutDate;
            return this;
        }
        public Builder withRoomCategory(RoomCategory roomCategory){
            this.instance.roomCategory = roomCategory;
            return this;
        }
        public Builder withRoomCount(Integer roomCount){
            this.instance.roomCount = roomCount;
            return this;
        }
        public Builder withCancellationDeadline(LocalDateTime cancellationDeadline){
            this.instance.cancellationDeadline = cancellationDeadline;
            return this;
        }
        public Builder withVoucherCode(String voucherCode){
            this.instance.voucherCode = voucherCode;
            return this;
        }
        public Builder withExtraServices(LinkedList<String> extraServices){
            this.instance.extraServices = extraServices;
            return this;
        }
        public Booking build(){
            Objects.requireNonNull(this.instance.id, "type must be set in booking");
            return this.instance;
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", customer='" + customer + '\'' +
                ", date=" + checkInDate +
                ", roomCategory=" + roomCategory +
                ", roomCount=" + roomCount +
                ", cancellationDeadline=" + cancellationDeadline +
                ", voucherCode='" + voucherCode + '\'' +
                ", extraServices=" + extraServices +
                '}';
    }
}
