package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    @Setter
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method); // Use setter for validation
        this.paymentData = paymentData;
        this.setStatus(status); // Use setter for validation
    }

    public void setStatus(String status) {
        if (status == null || status.isEmpty()) {
            this.status = status;
            return;
        }

        if (!status.equals("SUCCESS") && !status.equals("PENDING") && !status.equals("REJECTED")) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = status;
    }

    public void setMethod(String method) {
        if (method == null || method.isEmpty()) {
            this.method = method;
            return;
        }

        if (!method.equals("VOUCHER") && !method.equals("BANK_TRANSFER")) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
        this.method = method;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            this.id = id;
            return;
        }
        this.id = id;
    }
}