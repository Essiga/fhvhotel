package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CheckInServiceImpl implements CheckInService {
    @Autowired
    private BookingRepository bookingRepository;

    public CheckInServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Optional<List<Booking>> findTodaysCheckIns(){
        return bookingRepository.findTodaysCheckIns();
    }
}
