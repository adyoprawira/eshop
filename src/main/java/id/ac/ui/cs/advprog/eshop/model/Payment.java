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

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.paymentData = paymentData;
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            this.status = null;
            return;
        }

        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = PaymentStatus.valueOf(status);
    }

    public void setMethod(String method) {
        if (method == null || method.isEmpty()) {
            this.method = null;
            return;
        }

        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
        this.method = PaymentMethod.valueOf(method);
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            this.id = id;
            return;
        }
        this.id = id;
    }
}