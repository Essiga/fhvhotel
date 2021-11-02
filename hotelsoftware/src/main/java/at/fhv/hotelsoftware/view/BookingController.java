package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @Autowired
    private CreateBookingService createBookingService;

    @GetMapping("/index")
    public void createBooking(){

        Booking booking = Booking.builder().withId("10").withCustomer("adrian").build();

        createBookingService.createBooking(booking);

    }

}
