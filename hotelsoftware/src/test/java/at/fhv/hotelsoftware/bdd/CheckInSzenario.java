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
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@CucumberContextConfiguration
@SpringBootTest
public class CheckInSzenario extends SzenarioTxBoundary {

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

    @PersistenceContext
    EntityManager em;


    private List<RoomDTO> freeRoomsList = new LinkedList<>();
    private final BookingId bookingId = new BookingId(UUID.randomUUID());

    @Before
    public void beforeSzenario(){
        this.beginTX();
    }

    @After
    public void afterSzenario(){
        this.rollbackTX();
    }

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

    @Given("the booking is already created with {int} single and {int} double room and {int} superior room")
    public void setupBooking(Integer countSingleRooms, Integer countDoubleRooms, Integer countSuperiorRooms) {
        Booking booking = Booking.builder()
                .bookingId(bookingId)
                .checkInDate(LocalDate.now())
                .guestId(guestRepository.findAllGuests().get(0).getGuestId())
                .checkOutDate(LocalDate.now().plusDays(1))
                .singleRoom(countSingleRooms)
                .doubleRoom(countDoubleRooms)
                .superiorRoom(countSuperiorRooms)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();

        bookingRepository.addBooking(booking);
    }

    @Given("rooms already exist")
    public void setupRooms() {

        for (int i = 0; i < 10; i++)
        {
            Room singleRoom = Room.builder()
                    .roomCategory(RoomCategory.SINGLE)
                    .roomNumber(100 + i)
                    .roomStatus(RoomStatus.FREE)
                    .bookingId(null)
                    .build();

            this.em.persist(singleRoom);
        }

        for (int i = 0; i < 10; i++)
        {
            Room doubleRoom = Room.builder()
                    .roomCategory(RoomCategory.DOUBLE)
                    .roomNumber(200 + i)
                    .roomStatus(RoomStatus.FREE)
                    .bookingId(null)
                    .build();

            this.em.persist(doubleRoom);
        }

        for (int i = 0; i < 10; i++)
        {
            Room superiorRoom = Room.builder()
                    .roomCategory(RoomCategory.SUPERIOR)
                    .roomNumber(300 + i)
                    .roomStatus(RoomStatus.FREE)
                    .bookingId(null)
                    .build();

            this.em.persist(superiorRoom);
        }

        this.em.flush();
    }

    @Given("a list of free rooms is already generated")
    public void generateRooms() throws NotEnoughRoomsException, BookingNotFoundException {
        Booking booking = bookingRepository.findBookingById(bookingId).get();
        this.freeRoomsList = checkInService.findFreeRoomsForBooking(booking.getBookingId());
    }


    @When("I check in the booking")
    public void checkIn() throws RoomCategoryMismatchException, RoomAlreadyOccupiedException, DoubleRoomNumberException, BookingNotFoundException, RoomNotFoundException {
        checkInService.checkIn(bookingRepository.findBookingById(bookingId).get().getBookingId(), freeRoomsList);
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
        Booking booking = bookingRepository.findBookingById(bookingId).get();

        assertEquals(BookingStatus.CHECKEDIN, booking.getBookingStatus());
    }

    @Then("the room is assigned to the correct booking")
    public void expectCorrectBookingIdInRoom() throws RoomNotFoundException {
        List<RoomDTO> roomDTOs = viewRoomService.findAllRooms();
        Booking booking = bookingRepository.findBookingById(bookingId).get();

        for (RoomDTO roomDTO : roomDTOs) {
            if (freeRoomsList.contains(roomDTO)) {
                assertEquals(booking.getBookingId(), roomDTO.getBookingId());
            }
        }
    }
}
