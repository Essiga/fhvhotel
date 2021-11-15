package at.fhv.hotelsoftware.domain.model;

public class Id {
    private String bookingId;

    public Id(){}
    public Id(String string){
        this.bookingId = string;
    }

    public String getBookingId() {
        return bookingId;
    }
}
