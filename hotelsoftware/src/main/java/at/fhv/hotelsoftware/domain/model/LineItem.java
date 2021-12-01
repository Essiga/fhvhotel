package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class LineItem {

    private Long id;
    private String name;
    private int amount;
    private double price;

    public LineItem() {
    }

    public LineItem(String name, Integer amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o){

        // check for null and class
        if (o == null || getClass() != o.getClass()) {return false;}

        // same object by reference
        if (o == this) {return true;}

        LineItem lineItem = (LineItem)o;

        return (this.name == lineItem.getName() &&
                this.amount == lineItem.getAmount() &&
                this.price == lineItem.getPrice());
    }
}
