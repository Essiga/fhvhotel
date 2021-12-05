package at.fhv.hotelsoftware.domain.model.valueobjects;

public enum RoomCategory {
    SINGLE, DOUBLE, SUPERIOR;

    public double getPrice() {

        switch(this) {
            case SINGLE:
                return 40;
            case DOUBLE:
                return 60;
            case SUPERIOR:
                return 100;
        }

        return 0;
    }
}
