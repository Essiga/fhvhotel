package at.fhv.hotelsoftware.domain.model.exceptions;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException(String message){
        super(message);
    }
}
