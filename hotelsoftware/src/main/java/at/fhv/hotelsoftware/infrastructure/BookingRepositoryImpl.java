package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class BookingRepositoryImpl implements BookingRepository {
    private LinkedList<Booking> database = new LinkedList<>();

    public void addToDatabase(Booking booking){
        this.database.add(booking);
    }

    @Override
    public String toString() {
        return "BookingRepositoryImpl{" +
                "database=" + database +
                '}';
    }
}
