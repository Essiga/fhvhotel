package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.SplitInvoiceService;
import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.LineItem;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class SplitInvoiceServiceImpl implements SplitInvoiceService {
    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    @Override
    public InvoiceDTO splitInvoice(BookingId bookingId, InvoiceNumber invoiceNumber, List<LineItem> lineItems) throws BookingNotFoundException, InvoiceNotFoundException, NoLineItemsException, LineItemsMismatchException, AllLineItemsRemovedException {
        Optional<Booking> bookingOpt = bookingRepository.findBookingById(bookingId);

        if(bookingOpt.isEmpty()){
            throw new BookingNotFoundException("Booking with ID: " +bookingId.getBookingId().toString()+ " not found.");
        }

        Booking booking = bookingOpt.get();

        Invoice splitInvoice = booking.splitInvoice(invoiceNumber, lineItems);

        return InvoiceDTO.fromInvoice(splitInvoice);

    }
}
