package at.fhv.hotelsoftware.domain.model;

public class RoomAlreadyOccupiedException extends Exception{
    public RoomAlreadyOccupiedException(String message){
        super(message);
    }
}
