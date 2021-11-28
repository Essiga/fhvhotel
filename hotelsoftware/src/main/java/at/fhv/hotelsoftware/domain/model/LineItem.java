package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LineItem {

    private Long id;
    private RoomCategory roomCategory;
    private int roomCount;
    private double price;

    public LineItem(RoomCategory roomCategory, Integer roomCount, double price) {
        this.roomCategory = roomCategory;
        this.roomCount = roomCount;
        this.price = price;
    }

    @Override
    public boolean equals(Object o){

        // check for null and class
        if (o == null || getClass() != o.getClass()) {return false;}

        // same object by reference
        if (o == this) {return true;}

        LineItem lineItem = (LineItem)o;

        return (this.roomCategory == lineItem.getRoomCategory() &&
                this.roomCount == lineItem.getRoomCount() &&
                this.price == lineItem.getPrice());
    }
}
