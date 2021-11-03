package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import java.util.LinkedList;
import java.util.List;

@Component
public class BookingRepositoryImpl implements BookingRepository {
   // private LinkedList<Booking> database = new LinkedList<>();
    private List<Booking> allBookings = new LinkedList<>();

    /*
     public void addToDatabase(Booking booking){
        this.database.add(booking);
    }

    @Override
    public String toString() {
        return "BookingRepositoryImpl{" +
                "database=" + database +
                '}';
    } */

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Booking> findAllBookings() {
        TypedQuery<Booking>
                query = em.createQuery("SELECT e FROM Booking e", Booking.class);
        return query.getResultList();
    }



}

