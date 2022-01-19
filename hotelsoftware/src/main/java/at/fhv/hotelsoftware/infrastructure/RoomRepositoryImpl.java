package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Room> findAllFreeRooms() {
        TypedQuery<Room> query = this.em.createQuery("FROM Room WHERE room_status = 'FREE'", Room.class);
        List<Room> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Room> findAllRooms() {
        TypedQuery<Room> query = this.em.createQuery("FROM Room", Room.class);
        List<Room> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Room> findRoomsByBookingId(BookingId bookingId) {

        TypedQuery<Room> query = this.em.createQuery("FROM Room WHERE booking_id = :bookingId", Room.class);
        query.setParameter("bookingId", bookingId.getBookingId());
        List<Room> rooms = query.getResultList();

        return rooms;
    }

    @Override
    public Optional<Room> findRoomByRoomNumber(int roomNumber){
        TypedQuery<Room> query = this.em.createQuery("FROM Room WHERE room_number = :roomNumber", Room.class);
        query.setParameter("roomNumber", roomNumber);
        Optional<Room> room = query.getResultStream().findFirst();

        return room;
    }

    @Override
    public List<Booking> findFreeContingentOfRooms(LocalDate checkIn, LocalDate checkOut){
        TypedQuery<Booking> query = this.em.createQuery("From Booking Where (Check_in_date BETWEEN :checkIn AND :checkOut) AND (Check_out_date BETWEEN :checkIn AND :checkOut) AND Booking_status NOT LIKE 'COMPLETED'", Booking.class);
        query.setParameter("checkIn", checkIn); //find me: parameter were not set
        query.setParameter("checkOut", checkOut);
        List<Booking> result = query.getResultList();
        return result;
    }

    @Override
    public Integer findAllSingleRoomCount() {
        TypedQuery<Long> allSingleRooms = this.em.createQuery(
                "Select count (id) From Room Where room_category = 'SINGLE'", Long.class);
        return Math.toIntExact(allSingleRooms.getSingleResult());
    }

    @Override
    public Integer findAllDoubleRoomCount() {
        TypedQuery<Long> allDoubleRooms = this.em.createQuery(
                "Select count (id) From Room Where room_category= 'DOUBLE'", Long.class);
        return Math.toIntExact(allDoubleRooms.getSingleResult());
    }

    @Override
    public Integer findAllSuperiorRoomCount() {
        TypedQuery<Long> allSuperiorRooms = this.em.createQuery(
                "Select count (id) From Room Where room_category = 'SUPERIOR'", Long.class);
        return Math.toIntExact(allSuperiorRooms.getSingleResult());
    }
}
