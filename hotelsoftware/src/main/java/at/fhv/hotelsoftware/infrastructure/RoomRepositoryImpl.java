package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.Room;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class RoomRepositoryImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addRoom(Room room) {
        this.em.persist(room);
    }

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
}
