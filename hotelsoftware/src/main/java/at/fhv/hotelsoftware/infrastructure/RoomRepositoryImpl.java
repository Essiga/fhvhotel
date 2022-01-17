package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public List<Booking> findFreeContingentOfRooms(Date checkIn, Date checkOut){
        TypedQuery<Long> allSingleRooms = this.em.createQuery(
                "Select count (id) From Room Where room_category = 'SINGLE'", Long.class);
        int allSingleRoomsCount = Math.toIntExact(allSingleRooms.getSingleResult());

        TypedQuery<Long> allDoubleRooms = this.em.createQuery(
                "Select count (id) From Room Where room_category= 'DOUBLE'", Long.class);
        int allDoubleRoomsCount = Math.toIntExact(allDoubleRooms.getSingleResult());

        TypedQuery<Long> allSuperiorRooms = this.em.createQuery(
                "Select count (id) From Room Where room_category = 'SUPERIOR'", Long.class);
        int allSuperiorRoomsCount = Math.toIntExact(allSuperiorRooms.getSingleResult());

        TypedQuery<Booking> query = this.em.createQuery("From Booking Where (Check_in_date BETWEEN '2022-01-15' AND '2022-01-21') AND (Check_out_date BETWEEN '2022-01-15' AND '2022-01-21') AND Booking_status NOT LIKE 'COMPLETED'", Booking.class);
       // new AggregateJourneyFoodOrder()   TypedQuery<Room> query = this.em.createQuery("Select  SUM(single_room), SUM(double_room), SUM(superior_room) From Booking Where (check_in_date BETWEEN check_in_date = :checkIn AND check_out_date = :checkOut) AND (check_out_date BETWEEN check_in_date = :checkIn AND check_out_date = :checkOut) AND (booking_status NOT LIKE 'COMPLETED'\n)", Room.class);

        List<Booking> result = query.getResultList();

        return result;
    }
}
