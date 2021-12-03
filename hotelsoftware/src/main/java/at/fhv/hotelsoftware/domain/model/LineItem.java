package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LineItem {

    private Long id;
    private String name;
    private int amount;
    private double price;

    public LineItem(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public double getTotalPrice(){
        return amount * price;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (o == this)
            return true;

        LineItem lineItem = (LineItem) o;

        return (this.name == lineItem.getName() &&
                this.amount == lineItem.getAmount() &&
                this.price == lineItem.getPrice() );
    }
}
