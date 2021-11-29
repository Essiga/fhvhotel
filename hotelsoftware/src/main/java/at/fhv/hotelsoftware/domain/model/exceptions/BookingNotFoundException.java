package at.fhv.hotelsoftware.domain.model.exceptions;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String message){
        super(message);
    }
}
