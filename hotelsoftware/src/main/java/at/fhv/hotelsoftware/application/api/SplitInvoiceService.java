package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.application.dto.LineItemDTO;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.LineItem;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;

import java.util.List;

public interface SplitInvoiceService {
    InvoiceDTO splitInvoice(BookingId bookingId, InvoiceNumber invoiceNumber, List<LineItem> lineItems) throws BookingNotFoundException, InvoiceNotFoundException, NoLineItemsException, LineItemsMismatchException, AllLineItemsRemovedException;
}
