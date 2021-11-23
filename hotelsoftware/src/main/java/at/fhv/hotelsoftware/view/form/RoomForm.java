package at.fhv.hotelsoftware.view.form;

import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.RoomCategory;
import at.fhv.hotelsoftware.domain.model.RoomStatus;
import lombok.Data;

@Data
public class RoomForm {

    public Long id;
    private RoomCategory roomCategory;
    private Integer roomNumber;
    private RoomStatus roomStatus;
    private BookingId bookingId;

}
