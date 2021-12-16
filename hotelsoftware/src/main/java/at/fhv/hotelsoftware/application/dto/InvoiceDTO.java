package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestData;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


//TODO: Thaler: setter in DTO ok for thymeleaf or should we use forms?
@Data
@NoArgsConstructor
public class InvoiceDTO {

    private InvoiceNumber invoiceNumber;
    private LocalDate invoiceDate;
    private InvoiceStatus invoiceStatus;
    private List<LineItemDTO> lineItemDTOs;
    private GuestData guestData;
    private double sum;
    private double tax;
    private double sumWithTax;

    @Builder
    public InvoiceDTO(InvoiceNumber invoiceNumber, LocalDate invoiceDate, InvoiceStatus invoiceStatus, List<LineItem> lineItems, GuestData guestData, double sum, double tax, double sumWithTax) {
        List<LineItemDTO> lineItemDTOs = LineItemDTO.fromLineItemList(lineItems);
        this.invoiceDate = invoiceDate;

        this.invoiceNumber = invoiceNumber;
        this.invoiceStatus = invoiceStatus;
        this.lineItemDTOs = lineItemDTOs;
        this.guestData = guestData;
        this.sum = sum;
        this.tax = tax;
        this.sumWithTax = sumWithTax;
    }

    public static InvoiceDTO fromInvoice(Invoice invoice) {
        return new InvoiceDTO(invoice.getInvoiceNumber(),
                invoice.getInvoiceDate(),
                invoice.getInvoiceStatus(),
                invoice.getLineItems(),
                invoice.getGuestData(),
                invoice.getSum(),
                invoice.getTax(),
                invoice.getSumWithTax());
    }

    public static List<InvoiceDTO> fromInvoiceList(List<Invoice> invoices){

        return invoices
                .stream()
                .map(invoice ->
                        new InvoiceDTO(invoice.getInvoiceNumber(),
                                invoice.getInvoiceDate(),
                                invoice.getInvoiceStatus(),
                                invoice.getLineItems(),
                                invoice.getGuestData(),
                                invoice.getSum(),
                                invoice.getTax(),
                                invoice.getSumWithTax()))
                .collect(Collectors.toList());
    }
}
