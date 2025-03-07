package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VoucherPayment extends Payment {

    private String voucherCode;

    public VoucherPayment(String id, PaymentStatus status, String voucherCode) {
        super(id, PaymentMethod.VOUCHER, status, null); // Payment Data is null for voucher model.
        this.voucherCode = voucherCode;
    }

    // Implementation of getPaymentData() from Payment superclass.
    @Override
    public java.util.Map<String, String> getPaymentData() {
        return null; // Payment data is always null for VoucherPayment
    }

    // Implementation of setPaymentData() from Payment superclass.
    @Override
    public void setPaymentData(java.util.Map<String, String> paymentData) {
        // No implementation needed as paymentData is always null for VoucherPayment
    }

    //Implementation of setStatus from payment superclass.
    @Override
    public void setStatus(PaymentStatus status){
        super.setStatus(status);
    }

    //Implementation of setMethod from payment superclass.
    @Override
    public void setMethod(PaymentMethod method){
        super.setMethod(method);
    }

    //Implementation of setId from payment superclass.
    @Override
    public void setId(String id){
        super.setId(id);
    }

    //Implementation of setOrder from payment superclass.
    @Override
    public void setOrder(Order order){
        super.setOrder(order);
    }
}