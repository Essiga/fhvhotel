package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
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
    public List<Room> allRooms() {
        TypedQuery<Room> query = this.em.createQuery("FROM Room WHERE roomStatus = 'FREE'", Room.class);
        List<Room> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Room> roomByBookingId(BookingId bookingId) {
        TypedQuery<Room> query = this.em.createQuery("FROM Room WHERE booking_Id = bookingId", Room.class);
        List<Room> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Optional<Room> findByRoomNumber(Integer roomNumber){
        TypedQuery<Room> query = this.em.createQuery("FROM Room WHERE room_Number = roomNumber", Room.class);
        Optional<Room> room = query.getResultStream().findFirst();

        return room;
    }

}
