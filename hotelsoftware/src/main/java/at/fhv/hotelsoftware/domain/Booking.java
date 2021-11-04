package at.fhv.hotelsoftware.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Booking {

    private Id bookingId;
    private String customer;
    private FromDate fromDate;
    private ToDate toDate;
    private CancellationDeadLine cancellationDeadLine;
    private RoomCategory roomCategory;
    private Integer roomCount;
    private VoucherCode voucherCode;
   private BookingStatus bookingStatus;
    private Long id;
    //private LinkedList<String> extraServices;

    public static Builder builder(){return new Builder();}

    public Booking( ){
    }


  public static class Builder{

        private final Booking instance;


        public Builder() {
            this.instance = new Booking();
        }
        public Builder withId(Id id){
            this.instance.bookingId = id;
            return this;
        }
        public Builder withCustomer(String customer){
            this.instance.customer = customer;
            return this;
        }
        public Builder withFromDate(FromDate date){
            this.instance.fromDate = date;
            return this;
        }
        public Builder withToDate(ToDate date){
            this.instance.toDate = date;
            return this;
        }
        public Builder withancellationDeadLine(CancellationDeadLine date){
            this.instance.cancellationDeadLine = date;
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
        public Builder withVoucherCode(VoucherCode voucherCode){
            this.instance.voucherCode = voucherCode;
            return this;
        }
        public Builder withBookingStatus(BookingStatus bookingStatus){
            this.instance.bookingStatus = bookingStatus;
            return this;
        }

        /* public Builder withExtraServices(LinkedList<String> extraServices){
            this.instance.extraServices = extraServices;
            return this;
        } */


        public Booking build(){
            Objects.requireNonNull(this.instance.bookingId, "type must be set in booking");
            return this.instance;
        }
    }




//    @Override
//    public String toString() {
//        return "Booking{" +
//                "id='" + bookingId + '\'' +
//                ", customer='" + customer + '\'' +
//                //", date=" + date +
//                ", roomCategory=" + roomCategory +
//                ", roomCount=" + roomCount +
//               // ", cancellationDeadline=" + cancellationDeadline +
//                ", voucherCode='" + voucherCode + '\'' +
//                //", extraServices=" + extraServices +
//                '}';
//    }
}
