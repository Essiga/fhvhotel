package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateInvoiceService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.GuestNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateInvoiceServiceImpl implements CreateInvoiceService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Override
    @Transactional
    public void createInvoice(BookingId bookingId) throws BookingNotFoundException, GuestNotFoundException, InvoiceAlreadyCreatedException {
        Optional<Booking> bookingOpt = bookingRepository.findBookingById(bookingId);

        if(bookingOpt.isEmpty()){
            throw new BookingNotFoundException("Booking with ID: " + bookingId.getBookingId().toString() + " not found.");
        }

        Booking booking = bookingOpt.get();
        Optional<Guest> guestOpt = guestRepository.findGuestById(booking.getGuestId());

        if(guestOpt.isEmpty()){
            throw new GuestNotFoundException("Customer with ID: " + booking.getGuestId().getGuestId().toString() + " not found.");
        }
        Guest guest = guestOpt.get();

        booking.createInvoice(guest);

    }
}
