package at.fhv.hotelsoftware.domain.model;

public class InvoiceAlreadyCreatedException extends Exception{
    public InvoiceAlreadyCreatedException(String message) {
        super(message);
    }
}
