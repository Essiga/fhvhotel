package at.fhv.hotelsoftware.domain.model.exceptions;

public class GuestNotFoundException extends Exception {
    public GuestNotFoundException(String message) {
        super(message);
    }
}
