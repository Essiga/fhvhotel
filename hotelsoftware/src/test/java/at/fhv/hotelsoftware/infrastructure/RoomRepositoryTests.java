package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RoomRepositoryTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ViewRoomService viewRoomService;
//

    @Test
    public void given_rooms_when_persistedflushedfetched_then_expectallrooms() {
        Room singleRoom[] = new Room[10];
        Room doubleRoom[] = new Room[10];
        Room luxusRoom[] = new Room[10];

        int expectedNumberOfRooms = 30;

        for (int i = 0; i < singleRoom.length; i++) {
            singleRoom[i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SINGLE).
                    roomNumber(100 + i).build();
            viewRoomService.createRoom(singleRoom[i]);

            doubleRoom[i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.DOUBLE).
                    roomNumber(200 + i).build();
            viewRoomService.createRoom(doubleRoom[i]);

            luxusRoom[i] = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SUPERIOR).
                    roomNumber(300 + i).build();
            viewRoomService.createRoom(luxusRoom[i]);
        }

        List <Room> allRooms = roomRepository.findAllRooms();

        assertEquals(expectedNumberOfRooms, allRooms.size());

    }
}