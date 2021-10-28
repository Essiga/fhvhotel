package at.fhv.hotelsoftware.domain;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

public class Booking {
    private String id;
    private String customer;
    private LocalDateTime date;
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
        public Builder withDate(LocalDateTime date){
            this.instance.date = date;
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
                ", date=" + date +
                ", roomCategory=" + roomCategory +
                ", roomCount=" + roomCount +
                ", cancellationDeadline=" + cancellationDeadline +
                ", voucherCode='" + voucherCode + '\'' +
                ", extraServices=" + extraServices +
                '}';
    }
}
