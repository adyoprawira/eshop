package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
public class VoucherPayment extends Payment {

    @Setter
    private String voucherCode;

    public VoucherPayment(String id, PaymentStatus status, String voucherCode) {
        super(id, PaymentMethod.VOUCHER, status, null); // Payment Data is null for voucher model.
        this.voucherCode = voucherCode;
    }
}