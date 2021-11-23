package at.fhv.hotelsoftware.domain.model;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String message){
        super(message);
    }
}
