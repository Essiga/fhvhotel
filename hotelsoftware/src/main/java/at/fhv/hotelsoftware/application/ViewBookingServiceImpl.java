package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.ViewBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ViewBookingServiceImpl implements ViewBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findTodaysCheckIns() {
        List<Booking> todaysCheckIns = bookingRepository.findTodaysCheckIns();
        return todaysCheckIns
                .stream()
                .map(bookings ->
                        new BookingDTO(bookings.getBookingId(),
                                        bookings.getCustomer(),
                                        bookings.getFromDate(),
                                        bookings.getToDate(),
                                        bookings.getCancellationDeadLine(),
                                        bookings.getRoomCategory(),
                                        bookings.getVoucherCode(),
                                        bookings.getBookingStatus()))
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findAllBookings() {
        List<Booking> allBookings = bookingRepository.findAllBookings();
        return allBookings
                .stream()
                .map(bookings ->
                        new BookingDTO(bookings.getBookingId(),
                                bookings.getCustomer(),
                                bookings.getFromDate(),
                                bookings.getToDate(),
                                bookings.getCancellationDeadLine(),
                                bookings.getRoomCategory(),
                                bookings.getVoucherCode(),
                                bookings.getBookingStatus()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDTO> findTodaysCheckOuts() {
        List<Booking> allBookings = bookingRepository.findTodaysCheckOuts();
        return allBookings
                .stream()
                .map(bookings ->
                        new BookingDTO(bookings.getBookingId(),
                                bookings.getCustomer(),
                                bookings.getFromDate(),
                                bookings.getToDate(),
                                bookings.getCancellationDeadLine(),
                                bookings.getRoomCategory(),
                                bookings.getVoucherCode(),
                                bookings.getBookingStatus()))
                .collect(Collectors.toList());
    }
}
