package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.Iterator;
import java.util.Map;

public interface PaymentService {
    Payment addPayment(Order order, PaymentMethod method, Map<String, String> paymentData);
    Payment setStatus(Payment payment, PaymentStatus status);
    Payment getPayment(String paymentId);
    Iterator<Payment> getAllPayments();
    Payment updatePayment(Payment payment);
    void setOrder(Payment payment, Order order);
}