package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.BookingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

   /* @Transactional(readOnly = true)
    @Override
    public BookingDTO findBooking() {
        Booking bookings = bookingRepository.findBooking();
        return new BookingDTO(
                                bookings.getBookingId(),
                                bookings.getCustomer(),
                                bookings.getFromDate(),
                                bookings.getToDate(),
                                bookings.getCancellationDeadLine(),
                                bookings.getRoomCategory(),
                                bookings.getRoomCount(),
                                bookings.getVoucherCode(),
                                bookings.getBookingStatus());
    }*/

    @Transactional(readOnly = true)
    @Override
    public BookingDTO findBookingById(String bookingIdString) throws BookingNotFoundException {
        BookingId bookingId = new BookingId(bookingIdString);
        Optional<Booking> result = bookingRepository.findBookingById(bookingId);


        if(result.isEmpty()){
            throw new BookingNotFoundException("Couldn't find a booking under that ID");
        }

        Booking booking = result.get();
        return new BookingDTO(booking.getBookingId(),
                                booking.getCustomer(),
                                booking.getCheckInDate(),
                                booking.getCheckOutDate(),
                                booking.getCancellationDeadLine(),
                                booking.getRoomCategory(),
                                booking.getSingleRoom(),
                                booking.getDoubleRoom(),
                                booking.getLuxusRoom(),
                                booking.getVoucherCode(),
                                booking.getBookingStatus(),
                                booking.getRooms());
    }
}
