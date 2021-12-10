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
    private final double TAX = 0.2;

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
            sum += lineItem.getTotalPrice();
        }

        return sum;
    }


    public double getTax(){
        return getSum() * TAX;

    }

    public double getSumWithTax(){
        return getSum() + getTax();
    }
}
