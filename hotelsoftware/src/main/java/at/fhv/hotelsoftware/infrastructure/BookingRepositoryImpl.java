package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.Id;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class BookingRepositoryImpl implements BookingRepository {
   // private LinkedList<Booking> database = new LinkedList<>();
   // private List<Booking> allBookings = new LinkedList<>();

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
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Booking> findTodaysCheckIns() {
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE from_date = CURRENT_DATE() ", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }




    @Override
    public void addBooking(Booking booking) {
        this.em.merge(booking);         //merge instead of persist!!
    }
}

