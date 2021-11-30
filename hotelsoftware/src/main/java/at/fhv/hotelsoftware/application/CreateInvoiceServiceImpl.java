package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateInvoiceService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.CustomerRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.CustomerNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CreateInvoiceServiceImpl implements CreateInvoiceService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public void createInvoice(BookingId bookingId) throws BookingNotFoundException, CustomerNotFoundException, InvoiceAlreadyCreatedException {
        Optional<Booking> bookingOpt = bookingRepository.findBookingById(bookingId);

        if(bookingOpt.isEmpty()){
            throw new BookingNotFoundException("Booking with ID: " + bookingId.getBookingId().toString() + " not found.");
        }

        Booking booking = bookingOpt.get();
        Optional<Customer> customerOpt = customerRepository.findCustomerById(booking.getCustomerId());

        if(customerOpt.isEmpty()){
            throw new CustomerNotFoundException("Customer with ID: " + booking.getCustomerId().getCustomerId().toString() + " not found.");
        }
        Customer customer = customerOpt.get();

        booking.createInvoice(customer);

    }
}
