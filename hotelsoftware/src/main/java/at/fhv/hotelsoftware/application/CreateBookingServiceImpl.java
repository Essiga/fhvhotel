package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.view.form.BookingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class CreateBookingServiceImpl implements CreateBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public void createBooking(BookingForm bookingForm, CustomerId customerId){

        Booking booking = Booking.builder().
                                withBookingId(new BookingId(UUID.randomUUID())).
                                withCancellationDeadLine(null).
                                withCustomerId(customerId).
                                withBookingStatus(BookingStatus.PENDING).
                                withVoucherCode(new VoucherCode(bookingForm.getVoucherCode())).
                                withCheckInDate(LocalDate.parse(bookingForm.getCheckInDate())).
                                withCheckOutDate(LocalDate.parse(bookingForm.getCheckOutDate())).
                                withSingleRoom(bookingForm.getSingleRoomCount()).
                                withDoubleRoom(bookingForm.getDoubleRoomCount()).
                withSuperiorRoom(bookingForm.getSuperiorRoomCount()).
                                build();

        Customer customer = new Guest(new CustomerId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        try {
            booking.createInvoice(customer);
        } catch (InvoiceAlreadyCreatedException e) {
            e.printStackTrace();
        }
        bookingRepository.addBooking(booking);
    }


}
