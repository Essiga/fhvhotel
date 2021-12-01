package at.fhv.hotelsoftware.domain.model.exceptions;

public class RoomAlreadyOccupiedException extends Exception{
    public RoomAlreadyOccupiedException(String message){
        super(message);
    }
}
