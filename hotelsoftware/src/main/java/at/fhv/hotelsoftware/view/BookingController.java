package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.Booking;
import at.fhv.hotelsoftware.domain.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @Autowired
    private CreateBookingService createBookingService;

    @GetMapping("/index")
    public void createBooking(){

        Id id = new Id();
        //Booking booking = Booking.builder().withId(id).withCustomer("adrian").build();

        //createBookingService.createBooking(booking);

    }

}
