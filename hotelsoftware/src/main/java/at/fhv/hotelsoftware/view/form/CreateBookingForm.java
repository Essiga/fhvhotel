package at.fhv.hotelsoftware.view.form;

public class CreateBookingForm {
    private String bookingId;
    private String bookingText;

    public CreateBookingForm(String bookingId, String bookingText){
        this.bookingId = bookingId;
        this.bookingText = bookingText;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingText() {
        return bookingText;
    }

    public void setBookingText (String bookingText) {
        this.bookingText = bookingText;
    }

}
