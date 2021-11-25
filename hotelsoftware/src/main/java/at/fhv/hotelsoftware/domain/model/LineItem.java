package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LineItem {

    private RoomCategory roomCategory;
    private Integer roomCount;
    private Double price;
}
