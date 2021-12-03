package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.model.LineItem;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineItemTests {

    @Test
    public void given_existinglineitem_when_gettotalprice_then_reflecttotalprice(){
        //given
        int amount = 2;
        double price = RoomCategory.DOUBLE.getPrice();
        double expectedTotal = price * amount;

        LineItem lineItem = new LineItem(RoomCategory.DOUBLE.toString(), amount, RoomCategory.DOUBLE.getPrice());

        //when
        double totalPrice = lineItem.getTotalPrice();

        //then
        assertEquals(expectedTotal, totalPrice);

    }
}
