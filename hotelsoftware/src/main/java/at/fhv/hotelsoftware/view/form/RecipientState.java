package at.fhv.hotelsoftware.view.form;

import lombok.Data;

@Data
public class RecipientState {

    private boolean anonymous;

    public RecipientState() {
        this.anonymous = false;
    }
}
