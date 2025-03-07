package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class TransferPayment extends Payment {

    private String bankName;

    private String accountNumber;

    public TransferPayment(String id, PaymentStatus status, String bankName, String accountNumber) {
        super(id, PaymentMethod.BANK_TRANSFER, status, null);
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }

    // Override methods from Payment as needed
    @Override
    public java.util.Map<String, String> getPaymentData() {
        Map<String, String> data = new HashMap<>();
        data.put("bankName", this.bankName);
        data.put("accountNumber", this.accountNumber);
        return data;
    }

    @Override
    public void setPaymentData(java.util.Map<String, String> paymentData) {
        if (paymentData != null) {
            this.bankName = paymentData.get("bankName");
            this.accountNumber = paymentData.get("accountNumber");
        }
    }

    @Override
    public void setStatus(PaymentStatus status){
        super.setStatus(status);
    }

    @Override
    public void setMethod(PaymentMethod method){
        super.setMethod(method);
    }

    @Override
    public void setId(String id){
        super.setId(id);
    }

    @Override
    public void setOrder(Order order){
        super.setOrder(order);
    }
}