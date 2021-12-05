package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class BookingRepositoryImpl implements BookingRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addBooking(Booking booking) {
        this.em.persist(booking);
    }

    @Override
    public List<Booking> findTodaysCheckIns() {
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE check_in_date = CURRENT_DATE() and booking_status != 'CHECKEDIN' and booking_status != 'COMPLETED' ", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Optional<Booking> findBookingById(BookingId bookingId) {
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE booking_id = :bookingId", Booking.class);
        query.setParameter("bookingId", bookingId.getBookingId());
        Optional<Booking> booking = query.getResultStream().findFirst();

        return booking;
    }



    @Override
    public List<Booking> findTodaysCheckOuts() {
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE check_out_date = CURRENT_DATE() and booking_status = 'CHECKEDIN'", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }
}

