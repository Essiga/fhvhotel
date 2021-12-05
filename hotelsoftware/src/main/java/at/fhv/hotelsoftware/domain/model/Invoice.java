package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.model.valueobjects.GuestData;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class Invoice {

    private Long id;
    private InvoiceNumber invoiceNumber;
    private LocalDate invoiceDate;
    private InvoiceStatus invoiceStatus;
    private GuestData guestData;
    private List<LineItem> lineItems;

    @Builder
    public Invoice(InvoiceNumber invoiceNumber, List<LineItem> lineItems, GuestData guestData) {
        this.invoiceNumber = invoiceNumber;
        this.lineItems = lineItems;
        this.guestData = guestData;
        this.invoiceStatus = InvoiceStatus.OPEN;
        this.invoiceDate = LocalDate.now();
    }

    public double getSum() {

        double sum = 0.0;

        for (LineItem lineItem : lineItems) {
            int amount = lineItem.getAmount();
            double price = lineItem.getPrice();
            sum += price * amount;
        }

        return sum;
    }

    public double getTax(double sum){
        double tax = 0.2 * sum;
        return tax;
    }

    public double getTotalPrice(double sum, double tax){
        double totalPrice = sum + tax;
        return totalPrice;
    }
}
