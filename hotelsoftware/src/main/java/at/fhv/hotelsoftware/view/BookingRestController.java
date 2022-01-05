package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/booking")
public class BookingRestController {

    @Autowired
    ViewRoomService viewRoomService;


    @GetMapping("/getAllRooms")
    public List<RoomDTO> getAllRooms() throws RoomNotFoundException {
        return viewRoomService.findAllRooms();
    }

}
