package at.fhv.hotelsoftware.domain;

public enum BookingStatus {

    PENDING("Pending"), CONFIRMED("Confirmed"), CHECKEDIN("Checkedin"),  COMPLETED("Completed"), CANCELLED("Cancelled") ;

    private String bookingStatus;

    BookingStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }
}