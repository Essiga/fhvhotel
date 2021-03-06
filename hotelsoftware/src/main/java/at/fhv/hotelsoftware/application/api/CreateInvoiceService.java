package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;

public interface CreateInvoiceService {

    InvoiceDTO createInvoice(BookingId bookingId) throws BookingNotFoundException, GuestNotFoundException, InvoiceAlreadyCreatedException;
}
