package at.fhv.hotelsoftware.domain.model;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException(String message){
        super(message);
    }
}
