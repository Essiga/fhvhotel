package at.fhv.hotelsoftware.domain.model;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Invoice {

    private Long id;
    private InvoiceNumber invoiceNumber;
    private LocalDate invoiceDate;
    private InvoiceStatus invoiceStatus;
    private List<LineItem> lineItems;
    private GuestData guestData;

    public Invoice() {
    }

    public Invoice(InvoiceNumber invoiceNumber, List<LineItem> lineItems, GuestData guestData) {
        this.invoiceNumber = invoiceNumber;
        this.lineItems = lineItems;
        this.guestData = guestData;

        this.invoiceStatus = InvoiceStatus.OPEN;
        this.invoiceDate = LocalDate.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvoiceNumber(InvoiceNumber invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }


    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void setGuestData(GuestData guestData) {
        this.guestData = guestData;
    }

    public void addLineItems(List<LineItem> lineItems) {

        if (lineItems == null)
            return;

        if (this.lineItems == null) {
            this.lineItems = new ArrayList<>();
        }

        this.lineItems.addAll(lineItems);
    }

    public void addLineItem(LineItem lineItem) {

        if (lineItem == null)
            return;

        if (lineItems == null){
            lineItems = new ArrayList<>();
        }

        lineItems.add(lineItem);
    }

    public void removeAllLineItems() {
        this.lineItems.clear();
    }

    public void removeLineItem(LineItem lineItem) {

        if (lineItem == null || lineItems == null || lineItems.isEmpty())
            return;

        for (LineItem l : lineItems) {

            if (l.equals(lineItem)) {
                lineItems.remove(l);
                break;
            }
        }
    }

//    public LineItem removeLastLineItem() {
//        if (lineItems == null || lineItems.isEmpty())
//            return null;
//
//        int idxOfLastLineItem = lineItems.size()-1;
//        LineItem lastLineItem = lineItems.get(idxOfLastLineItem);
//        lineItems.remove(lastLineItem);
//
//        return lastLineItem;
//    }
//
//    public LineItem removeFirstLineItem() {
//        if (lineItems == null || lineItems.isEmpty())
//            return null;
//
//        LineItem firstLineItem = lineItems.get(0);
//        lineItems.remove(firstLineItem);
//
//        return firstLineItem;
//    }

    public double getSum(){

        double sum = 0.0;

        for (LineItem lineItem : lineItems){
            int amount = lineItem.getAmount();
            double price = lineItem.getPrice();
            sum += price * amount;
        }

        return sum;
    }
}
