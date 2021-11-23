package at.fhv.hotelsoftware.domain.model;

import lombok.Data;

//TODO: Ask Jonathan about using lombok annotations and private setters

public class VoucherCode {
    private String voucherCode;

    public VoucherCode(){}
    public VoucherCode(String string){
        this.voucherCode = string;
    }


    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }
}
