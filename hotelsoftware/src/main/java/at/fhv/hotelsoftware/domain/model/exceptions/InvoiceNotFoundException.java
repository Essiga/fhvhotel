package at.fhv.hotelsoftware.domain.model.exceptions;

public class InvoiceNotFoundException extends Exception{
    public InvoiceNotFoundException(String message) {
        super(message);
    }
}
