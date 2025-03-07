package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, PaymentMethod method, Map<String, String> paymentData) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        String id = UUID.randomUUID().toString();
        Payment payment = new Payment(id, method, PaymentStatus.PENDING, paymentData);
        payment.setOrder(order);
        return paymentRepository.create(payment);
    }

    @Override
    public Payment setStatus(Payment payment, PaymentStatus status) {
        payment.setStatus(status);
        return paymentRepository.update(payment);
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public Iterator<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment updatePayment(Payment payment){
        return paymentRepository.update(payment);
    }

    @Override
    public void setOrder(Payment payment, Order order) {
        payment.setOrder(order);
    }
}