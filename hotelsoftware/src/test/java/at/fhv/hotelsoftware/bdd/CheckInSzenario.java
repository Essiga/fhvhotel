package at.fhv.hotelsoftware.bdd;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.api.RoomRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@CucumberContextConfiguration
@SpringBootTest
public class CheckInSzenario {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    CheckInService checkInService;

    @Autowired
    ViewRoomService viewRoomService;

    private List<RoomDTO> freeRoomsList = new LinkedList<>();

    @Transactional
    @Given("the guest is already created")
    public void setupGuest() {
        Guest guest = Guest.builder()
                .guestId(new GuestId(UUID.randomUUID()))
                .firstName("Max")
                .lastName("Mustermann")
                .phoneNumber("0660123456")
                .city("Dornbirn")
                .street("Jahngasse 1")
                .zip("6850")
                .country("Austria")
                .email("max.mustermann@gmail.com")
                .build();

        guestRepository.addGuest(guest);

    }

    @Transactional
    @Given("the booking is already created with {int} single and {int} double room and {int} superior room")
    public void setupBooking(Integer countSingleRooms, Integer countDoubleRooms, Integer countSuperiorRooms) {
        Booking booking = Booking.builder()
                .bookingId(new BookingId(UUID.randomUUID()))
                .checkInDate(LocalDate.now())
                .guestId(guestRepository.findAllGuest().get(0).getGuestId())
                .checkOutDate(LocalDate.now().plusDays(1))
                .singleRoom(countSingleRooms)
                .doubleRoom(countDoubleRooms)
                .superiorRoom(countSuperiorRooms)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        bookingRepository.addBooking(booking);
    }


    @Given("there is at least {int} free single room, {int} free double room and {int} free superior room")
    public void setupRooms(int countSingleRooms, int countDoubleRooms, int countSuperiorRooms) {
        for (int i = 0; i < countSingleRooms; i++) {
            Room singleRoom = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SINGLE).
                    roomNumber(100 + i).build();

            viewRoomService.createRoom(singleRoom);
        }

        for (int i = 0; i < countDoubleRooms; i++) {
            Room doubleRoom = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.DOUBLE).
                    roomNumber(200 + i).build();

            viewRoomService.createRoom(doubleRoom);
        }

        for (int i = 0; i < countSuperiorRooms; i++) {
            Room superiorRoom = Room.builder().
                    roomStatus(RoomStatus.FREE).
                    bookingId(null).
                    roomCategory(RoomCategory.SUPERIOR).
                    roomNumber(300 + i).build();

            viewRoomService.createRoom(superiorRoom);
        }
    }


    @Given("a list of free rooms is already generated")
    public void generateRooms() throws NotEnoughRoomsException, BookingNotFoundException {
        Booking booking = bookingRepository.findAllBookings().get(0);
        this.freeRoomsList = checkInService.findFreeRoomsForBooking(booking.getBookingId());
    }


    @When("I check in the booking")
    public void checkIn() throws RoomCategoryMismatchException, RoomAlreadyOccupiedException, DoubleRoomNumberException, BookingNotFoundException, RoomNotFoundException {
        checkInService.checkIn(bookingRepository.findAllBookings().get(0).getBookingId(), freeRoomsList);
    }


    @Then("the room status should change from free to occupied for all rooms in the booking")
    public void expectOccupiedRooms() throws RoomNotFoundException {

        List<RoomDTO> roomDTOs = viewRoomService.findAllRooms();

        for (RoomDTO roomDTO : roomDTOs) {
            if (freeRoomsList.contains(roomDTO)) {
                assertEquals(RoomStatus.OCCUPIED, roomDTO.getRoomStatus());
            }
        }

    }


    @Then("the booking status should change from confirmed to checkedin")
    public void expectCorrectBookingStatus() {
        Booking booking = bookingRepository.findAllBookings().get(0);

        assertEquals(BookingStatus.CHECKEDIN, booking.getBookingStatus());
    }

    @Then("the room is assigned to the correct booking")
    public void expectCorrectBookingIdInRoom() throws RoomNotFoundException {
        List<RoomDTO> roomDTOs = viewRoomService.findAllRooms();
        Booking booking = bookingRepository.findAllBookings().get(0);

        for (RoomDTO roomDTO : roomDTOs) {
            if (freeRoomsList.contains(roomDTO)) {
                assertEquals(booking.getBookingId(), roomDTO.getBookingId());
            }
        }
    }
}
