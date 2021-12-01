package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ViewBookingServiceImpl implements ViewBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findTodaysCheckIns() {
        List<Booking> todaysCheckIns = bookingRepository.findTodaysCheckIns();

        return BookingDTO.fromBookingList(todaysCheckIns);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findAllBookings() {
        List<Booking> allBookings = bookingRepository.findAllBookings();
        return BookingDTO.fromBookingList(allBookings);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findTodaysCheckOuts() {
        List<Booking> allBookings = bookingRepository.findTodaysCheckOuts();
        return BookingDTO.fromBookingList(allBookings);
    }

    @Transactional(readOnly = true)
    @Override
    public BookingDTO findBookingById(String bookingIdString) throws BookingNotFoundException {
        Optional<Booking> bookingOpt = bookingRepository.findBookingById(new BookingId(bookingIdString));

        if(bookingOpt.isEmpty()){
            throw new BookingNotFoundException("Booking with ID: " + bookingIdString + " not found");
        }

        return BookingDTO.fromBooking(bookingOpt.get());
    }

}
