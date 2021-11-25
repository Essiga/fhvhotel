package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.domain.model.RoomCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomCategoryTests {

    @Test
    public void given_roomcategory_when_getprice_then_getcorrectprice(){
        //given
        double expectedPrice = 40.0;
        //when
        double price = RoomCategory.SINGLE.getPrice();

        //then
        assertEquals(expectedPrice, price);
    }
}
