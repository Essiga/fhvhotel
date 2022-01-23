package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VoucherCode {

    private String voucherCode = " ";

    public VoucherCode(String string) {
        this.voucherCode = string;
    }
}
