package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private PaymentMethod method;
    private PaymentStatus status;
    @Setter
    private Map<String, String> paymentData;

    public Payment(String id, PaymentMethod method, PaymentStatus status, Map<String, String> paymentData) {
        this.setId(id); // Call setId() to validate the ID
        this.setMethod(method);
        this.paymentData = paymentData;
        this.setStatus(status);
    }

    public void setStatus(PaymentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Payment status cannot be null.");
        }
        this.status = status;
    }

    public void setMethod(PaymentMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Payment method cannot be null.");
        }
        this.method = method;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        }
        this.id = id;
    }
}