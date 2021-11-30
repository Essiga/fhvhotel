package at.fhv.hotelsoftware.application.api;

import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.view.form.GuestForm;

public interface CreateGuestService {

    GuestId createGuest(GuestForm guestForm);
}
