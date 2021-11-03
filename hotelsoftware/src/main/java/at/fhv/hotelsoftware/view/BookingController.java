package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CheckInService;
import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.application.dto.BookingDTO;
import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private CreateBookingService createBookingService;

    @Autowired
    private CheckInService checkInService;

    @GetMapping("/index")
    public void createBooking(){

        List<BookingDTO> bookingDTO = checkInService.getTodaysCheckIns();

//        Booking booking = Booking.builder().withId("10").withCustomer("adrian").build();
//
//        createBookingService.createBooking(booking);

    }

}
