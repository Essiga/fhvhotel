package at.fhv.hotelsoftware.domain.model.exceptions;

public class NoLineItemsException extends Exception{
    public NoLineItemsException(String message) {
        super(message);
    }
}
