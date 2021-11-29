package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.model.InvoiceAlreadyCreatedException;

public interface CreateInvoiceService {
    void createInvoice(BookingId bookingId) throws BookingNotFoundException, CustomerNotFoundException, InvoiceAlreadyCreatedException;
}
