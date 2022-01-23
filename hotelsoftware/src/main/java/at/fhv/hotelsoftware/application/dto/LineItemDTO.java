package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.LineItem;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data //required by Hibernate
@NoArgsConstructor
@EqualsAndHashCode
public class LineItemDTO {

    private String name;
    private int amount;
    private int splitAmount;
    private int duration;
    private double price;
    private double totalPrice;

    @Builder
    public LineItemDTO(String name, int amount, int duration, double price, double totalPrice) {
        this.name = name;
        this.amount = amount;
        this.duration = duration;
        this.price = price;
        this.totalPrice = totalPrice;
    }



    public static List<LineItemDTO> fromLineItemList(List<LineItem> lineItems) {
        return lineItems
                .stream()
                .map(lineItem ->
                        new LineItemDTO(lineItem.getName(),
                                lineItem.getAmount(),
                                lineItem.getDuration(),
                                lineItem.getPrice(),
                                lineItem.getTotalPrice()))
                .collect(Collectors.toList());
    }
}
