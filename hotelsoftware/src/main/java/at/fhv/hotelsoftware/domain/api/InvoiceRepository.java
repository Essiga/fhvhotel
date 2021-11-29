package at.fhv.hotelsoftware.domain.api;

import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.InvoiceNumber;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface InvoiceRepository {

    void addInvoice(Invoice invoice);
    Optional<Invoice> findInvoiceByInvoiceNumber(InvoiceNumber invoiceNumber);
    List<Invoice> findInvoicesByBookingId(BookingId bookingId);
}
