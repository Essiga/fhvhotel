package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookingRepositoryImpl implements BookingRepository {

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
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE check_in_date = CURRENT_DATE() and booking_status != 'CHECKEDIN' ", Booking.class);
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
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE check_out_date = CURRENT_DATE() ", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public void addBooking(Booking booking) {
        this.em.persist(booking);
    }

}

