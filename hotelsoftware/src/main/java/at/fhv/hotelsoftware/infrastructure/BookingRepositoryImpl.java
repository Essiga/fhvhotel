package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
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
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE trunc(check_in_date) = trunc(current_date()) and booking_status = 'CONFIRMED'", Booking.class);
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
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking WHERE trunc(check_out_date) = trunc(current_date()) and booking_status = 'CHECKEDIN'", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }
    @Override
    public List<Booking> findAllBookings() {
        TypedQuery<Booking> query = this.em.createQuery("FROM Booking", Booking.class);
        List<Booking> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Booking> findBookingsByDate(LocalDate checkIn, LocalDate checkOut){
        TypedQuery<Booking> query = this.em.createQuery("From Booking Where (Check_in_date BETWEEN :checkIn AND :checkOut) AND (Check_out_date BETWEEN :checkIn AND :checkOut) AND Booking_status NOT LIKE 'COMPLETED'", Booking.class);
        query.setParameter("checkIn", checkIn);
        query.setParameter("checkOut", checkOut);
        List<Booking> result = query.getResultList();
        return result;
    }
}

