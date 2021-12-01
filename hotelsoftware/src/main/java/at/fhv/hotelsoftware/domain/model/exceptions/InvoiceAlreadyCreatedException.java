package at.fhv.hotelsoftware.domain.model.exceptions;

public class InvoiceAlreadyCreatedException extends Exception{
    public InvoiceAlreadyCreatedException(String message) {
        super(message);
    }
}
