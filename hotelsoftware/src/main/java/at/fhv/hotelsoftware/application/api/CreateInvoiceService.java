package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;

public interface CreateInvoiceService {
    void createInvoice(BookingId bookingId) throws BookingNotFoundException, CustomerNotFoundException, InvoiceAlreadyCreatedException;
}
