package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingDTO> getTodaysCheckIns(){
        return bookingRepository.getTodaysCheckIns();
    }
}
