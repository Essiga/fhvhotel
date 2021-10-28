package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.Booking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelsoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsoftwareApplication.class, args);
		final Booking booking = Booking.builder().withId("100").build();
		System.out.printf(booking.toString());
	}



}
