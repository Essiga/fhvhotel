package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.GuestId;
import at.fhv.hotelsoftware.view.form.CustomerForm;

public interface CreateGuestService {

    GuestId createGuest(CustomerForm customerForm);
}
