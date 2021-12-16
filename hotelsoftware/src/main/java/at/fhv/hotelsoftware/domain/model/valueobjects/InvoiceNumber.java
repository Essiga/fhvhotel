package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

//TODO: Jonathan: Required by thymeleaf or else the invoicenumber is empty
@Data
@NoArgsConstructor
public class InvoiceNumber {

    private UUID invoiceNumber;

    public InvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = UUID.fromString(invoiceNumber);
    }

    public InvoiceNumber(UUID invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
