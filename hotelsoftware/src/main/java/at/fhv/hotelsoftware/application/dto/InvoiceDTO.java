package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {

    private InvoiceNumber invoiceNumber;
    private LocalDate invoiceDate;
    private InvoiceStatus invoiceStatus;
    private List<LineItem> lineItems;
    private CustomerData customerData;

    public static InvoiceDTO fromInvoice(Invoice invoice){
        return new InvoiceDTO(invoice.getInvoiceNumber(),
                invoice.getInvoiceDate(),
                invoice.getInvoiceStatus(),
                invoice.getLineItems(),
                invoice.getCustomerData());
    }

    public static List<InvoiceDTO> fromInvoiceList(List<Invoice> invoices){
        return invoices
                .stream()
                .map(invoice ->
                        new InvoiceDTO(invoice.getInvoiceNumber(),
                                invoice.getInvoiceDate(),
                                invoice.getInvoiceStatus(),
                                invoice.getLineItems(),
                                invoice.getCustomerData()))
                .collect(Collectors.toList());
    }
}
