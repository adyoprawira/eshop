package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentRepository {

    private List<Payment> paymentData = new ArrayList<>();

    public Payment create(Payment payment) {
        paymentData.add(payment);
        return payment;
    }

    public Iterator<Payment> findAll() {
        return paymentData.iterator();
    }

    public Payment findById(String paymentId) {
        for (Payment payment : paymentData) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public Payment update(Payment payment) {
        if (payment.getId() == null || payment.getId().isEmpty()) {
            return null;
        }

        for (int i = 0; i < paymentData.size(); i++) {
            if (paymentData.get(i).getId().equals(payment.getId())) {
                paymentData.set(i, payment);
                return payment;
            }
        }
        return null;
    }
}