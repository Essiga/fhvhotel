package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class BookingRepositoryImpl implements BookingRepository {
    private LinkedList<Booking> database = new LinkedList<>();

    public void addToDatabase(Booking booking){
        this.database.add(booking);
    }

    public List<BookingDTO> getTodaysCheckIns() {
        List<BookingDTO> dummy = new LinkedList<BookingDTO>();
        return dummy;

    }

    @Override
    public String toString() {
        return "BookingRepositoryImpl{" +
                "database=" + database +
                '}';
    }
}
