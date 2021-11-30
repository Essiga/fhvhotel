package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data //setter required by Hibernate
@NoArgsConstructor
public class BookingId {

    private UUID bookingId;

    public BookingId(String id) {
        this.bookingId = UUID.fromString(id);
    }

    public BookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }
}
