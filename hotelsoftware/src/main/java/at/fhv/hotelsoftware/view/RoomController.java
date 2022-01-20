package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.ViewRoomService;
import at.fhv.hotelsoftware.application.dto.RoomDTO;
import at.fhv.hotelsoftware.domain.model.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    ViewRoomService viewRoomService;

    private static final String ROOM_OVERVIEW = "/roomOverview";
    private static final String CLEAN_ROOM = "/cleanRoom";
    private static final String ERROR_URL = "/showErrorPage";

    @GetMapping(ROOM_OVERVIEW)
    public ModelAndView roomOverview(Model model) throws ParseException {

        try {
            List<RoomDTO> allRooms = viewRoomService.findAllRooms();
            model.addAttribute("allRooms", allRooms);

        }catch (RoomNotFoundException e){
            redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("roomOverview");
    }


    @GetMapping(CLEAN_ROOM)
    public ModelAndView cleanRoom(@RequestParam("roomNumber") String roomNumberString, Model model) throws RoomNotFoundException {
        viewRoomService.clean(roomNumberString);
        List<RoomDTO> allRooms = viewRoomService.findAllRooms();
        model.addAttribute("allRooms", allRooms);
        return new ModelAndView("roomOverview");
    }

    private static ModelAndView redirectToErrorPage (String errorMessage){
        return new ModelAndView("redirect:" + ERROR_URL + "?errorMessage=" + errorMessage);
    }
}
