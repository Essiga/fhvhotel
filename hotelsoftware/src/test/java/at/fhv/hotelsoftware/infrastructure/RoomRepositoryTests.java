package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
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
    //
    @Test
    void given_newroom_when_persistedflushedfetched_then_expectequalroom() {
        //given
        Integer roomNumberExpected = 101;

        Room roomExpected = new Room().builder().
                roomNumber(roomNumberExpected).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.SINGLE).
                build();

        //when
        roomRepository.addRoom(roomExpected);
        em.flush();
        Optional<Room> roomActual = roomRepository.findRoomByRoomNumber(roomNumberExpected);

        //then
        assertEquals(roomNumberExpected, roomActual.get().getRoomNumber());
        assertEquals(RoomStatus.FREE, roomActual.get().getRoomStatus());
        assertEquals(RoomCategory.SINGLE, roomActual.get().getRoomCategory());
    }

    @Test
    void given_roomspersisted_when_findallrooms_then_returnallrooms() {
        //given
        Room roomExpected1 = new Room().builder().
                roomNumber(101).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.SINGLE).
                build();

        Room roomExpected2 = new Room().builder().
                roomNumber(102).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.SINGLE).
                build();

        Room roomExpected3 = new Room().builder().
                roomNumber(201).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.DOUBLE).
                build();

        List<Room> expectedRooms = new LinkedList<>();
        expectedRooms.add(roomExpected1);
        expectedRooms.add(roomExpected2);
        expectedRooms.add(roomExpected3);

        roomRepository.addRoom(roomExpected1);

        roomRepository.addRoom(roomExpected2);

        roomRepository.addRoom(roomExpected3);
        em.flush();

        //when
        List<Room> rooms = roomRepository.findAllRooms();

        //then
        assertEquals(expectedRooms.size(), rooms.size());
        for(int i = 0; i < rooms.size(); i++) {
            assertEquals(expectedRooms.get(i), rooms.get(i));
        }

    }

    @Test
    public void given_checkedinrooms_when_findroomsbybookingid_returnrooms() {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());

        Room roomExpected1 = new Room().builder().
                roomNumber(101).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.SINGLE).
                bookingId(bookingId).
                build();

        Room roomExpected2 = new Room().builder().
                roomNumber(102).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.SINGLE).
                bookingId(bookingId).
                build();

        Room roomExpected3 = new Room().builder().
                roomNumber(201).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.DOUBLE).
                bookingId(bookingId).
                build();

        Room roomNotExpected = new Room().builder().
                roomNumber(201).
                roomStatus(RoomStatus.FREE).
                roomCategory(RoomCategory.DOUBLE).
                bookingId(new BookingId(UUID.randomUUID())).
                build();

        List<Room> expectedRooms = new LinkedList<>();
        expectedRooms.add(roomExpected1);
        expectedRooms.add(roomExpected2);
        expectedRooms.add(roomExpected3);

        roomRepository.addRoom(roomExpected1);

        roomRepository.addRoom(roomExpected2);

        roomRepository.addRoom(roomExpected3);

        roomRepository.addRoom(roomNotExpected);
        em.flush();

        //when
        List<Room> rooms = roomRepository.findRoomsByBookingId(bookingId);

        //then
        assertEquals(expectedRooms.size(), rooms.size());
        for(int i = 0; i < rooms.size(); i++) {
            assertEquals(expectedRooms.get(i), rooms.get(i));
        }

    }
}
