package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private CreateBookingService createBookingService;

    @Autowired
    private CheckInService checkInService;

    @GetMapping("/index")
    public void createBooking(){

        Optional<List<Booking>> test = createBookingService.findAllBookings();
        System.out.println("test");
        Optional<List<Booking>> test1 = checkInService.findTodaysCheckIns();

        //Id id = new Id();
        //Booking booking = Booking.builder().withId(id).withCustomer("adrian").build();

        //createBookingService.createBooking(booking);

    }

}
