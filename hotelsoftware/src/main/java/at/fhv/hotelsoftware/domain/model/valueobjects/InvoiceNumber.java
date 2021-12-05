package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class InvoiceNumber {

    private UUID invoiceNumber;

    public InvoiceNumber(UUID invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
