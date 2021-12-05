package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;

import java.util.List;

public interface ViewInvoiceService {

    List<InvoiceDTO> findInvoiceByBookingId(BookingId bookingId) throws BookingNotFoundException;
}
