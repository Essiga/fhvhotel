package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestData;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class InvoiceDTO {

    private InvoiceNumber invoiceNumber;
    private LocalDate invoiceDate;
    private InvoiceStatus invoiceStatus;
    private List<LineItem> lineItems;
    private GuestData guestData;

    @Builder
    public InvoiceDTO(InvoiceNumber invoiceNumber, LocalDate invoiceDate, InvoiceStatus invoiceStatus, List<LineItem> lineItems, GuestData guestData) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceStatus = invoiceStatus;
        this.lineItems = lineItems;
        this.guestData = guestData;
    }

    public static List<InvoiceDTO> fromInvoiceList(List<Invoice> invoices){

        return invoices
                .stream()
                .map(invoice ->
                        new InvoiceDTO(invoice.getInvoiceNumber(),
                                invoice.getInvoiceDate(),
                                invoice.getInvoiceStatus(),
                                invoice.getLineItems(),
                                invoice.getGuestData()))
                .collect(Collectors.toList());
    }
}
