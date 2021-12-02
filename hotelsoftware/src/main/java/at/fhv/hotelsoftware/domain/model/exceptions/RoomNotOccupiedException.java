package at.fhv.hotelsoftware.domain.model.exceptions;

public class RoomNotOccupiedException extends Exception {
    public RoomNotOccupiedException(String message){
        super(message);
    }
}
