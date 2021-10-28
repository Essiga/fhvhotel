package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.application.CreateBookingServiceImpl;
import at.fhv.hotelsoftware.application.api.CreateBookingService;
import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelsoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsoftwareApplication.class, args);
		System.out.println("test");
		final Booking booking = Booking.builder().withId("100").withCustomer("achim").build();
		CreateBookingService createBookingService = new CreateBookingServiceImpl();
		createBookingService.createBooking(booking);
	}
}
