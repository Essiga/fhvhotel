package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.Dummy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookingController {

    private static final String DASHBOARD_URL = "/";
    private static final String CREATE_CUSTOMER_URL = "/createCustomer";
    private static final String CHOOSE_ROOM_URL = "/chooseRoom";
    private static final String EXTRA_SERVICE_URL = "/extraService";
    private static final String BOOKING_SUMMARY_URL = "/bookingSummary";
    private static final String WRITE_BOOKING_IN_DB = "/writeBookingInDatabase";

    //private static final String ADD_EXTRA_SERVICES_URL = "/addExtraServices";
    //private static final String CREATE_BOOKING_URL = "/createBooking";
    //private static final String ERROR_URL = "/error";

    private static final String ERROR_VIEW = "errorView";

    @GetMapping(DASHBOARD_URL)
    public String showDashboard() {
        return "dashboard";
    }

    // @PostMapping("/createCustomer")
    // public ModelAndView showCustomerForm(Model model) {
    //     Customer customer = new Customer();
    //     model.addAttribute("customer", customer);
    //     return new ModelAndView("/createCustomer");
    // }

    @GetMapping(CREATE_CUSTOMER_URL)
    public ModelAndView showCustomerForm(Model model) {
        Dummy dummy = new Dummy();
        model.addAttribute("dummy", dummy);
        return new ModelAndView("createCustomer");
    }
/*
    @PostMapping("/createCustomer")
    public String submitCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        System.out.println("CUSTOMER" + customer);
        model.addAttribute("customer", customer);
        return "chooseRoom";
    }
*/

    @PostMapping(CHOOSE_ROOM_URL)
    public ModelAndView submitChooseRoom(@ModelAttribute("dummy") Dummy dummy, Model model) {
        model.addAttribute("dummy", dummy);
        return new ModelAndView("chooseRoom");
    }

    // @PostMapping("/chooseRoom")
    // public String submitChooseRoom(@ModelAttribute("customer") Customer customer, Model model) {
    //     System.out.println("customer existing in room POST: " + customer);
    //     model.addAttribute("customer", customer);

    //     return "/extraService";
    // }

    // @GetMapping("/extraService")
    // public ModelAndView showExtraServices(@ModelAttribute("customer") Customer customer, Model model) {
    //     System.out.println("customer existing in extra GET: " + customer);
    //     model.addAttribute("customer", customer);
    //     return new ModelAndView("/extraService");
    // }

    @PostMapping(EXTRA_SERVICE_URL)
    public ModelAndView submitExtraService(@ModelAttribute("dummy") Dummy dummy, Model model) {
        model.addAttribute("dummy", dummy);
        return new ModelAndView("extraService");
    }

    @PostMapping(BOOKING_SUMMARY_URL)
    public ModelAndView submitBookingSummary(@ModelAttribute("dummy") Dummy dummy, Model model) {
        model.addAttribute("dummy", dummy);
        return new ModelAndView("bookingSummary");
    }

    @PostMapping(WRITE_BOOKING_IN_DB)
    public ModelAndView writeBookingInDatabase(@ModelAttribute("dummy") Dummy dummy, Model model) {
        model.addAttribute("dummy", dummy);
        return new ModelAndView("dashboard");
    }
}