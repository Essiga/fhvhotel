package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewInvoiceService;
import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ViewInvoiceServiceImpl implements ViewInvoiceService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceDTO> findInvoiceByBookingId(BookingId bookingId) throws BookingNotFoundException {
        Optional<Booking> bookingOpt = bookingRepository.findBookingById(bookingId);

        if(bookingOpt.isEmpty()){
            throw new BookingNotFoundException("Booking with ID: " + bookingId.getBookingId().toString() + " not found.");
        }

        Booking booking = bookingOpt.get();

        return InvoiceDTO.fromInvoiceList(booking.getInvoices());

    }
}
