package at.fhv.hotelsoftware.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceNumber {
    private UUID invoiceNumber;
}
