package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService; // Corrected line

    private Order order;
    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        paymentData = new HashMap<>();
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(payment);

        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.VOUCHER, paymentData);

        assertNotNull(createdPayment);
        assertEquals(payment, createdPayment);
        verify(paymentRepository, times(1)).create(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        when(paymentRepository.update(any(Payment.class))).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.SUCCESS);

        assertEquals(PaymentStatus.SUCCESS, updatedPayment.getStatus());
        verify(paymentRepository, times(1)).update(any(Payment.class));
    }

    @Test
    void testSetStatusRejected() {
        when(paymentRepository.update(any(Payment.class))).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.REJECTED);

        assertEquals(PaymentStatus.REJECTED, updatedPayment.getStatus());
        verify(paymentRepository, times(1)).update(any(Payment.class));
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById("1")).thenReturn(payment);

        Payment retrievedPayment = paymentService.getPayment("1");

        assertEquals(payment, retrievedPayment);
        verify(paymentRepository, times(1)).findById("1");
    }

    @Test
    void testGetPaymentNotFound() {
        when(paymentRepository.findById("2")).thenReturn(null);

        Payment retrievedPayment = paymentService.getPayment("2");

        assertNull(retrievedPayment);
        verify(paymentRepository, times(1)).findById("2");
    }

    @Test
    void testGetAllPayments() {
        Iterator<Payment> iterator = mock(Iterator.class);
        when(paymentRepository.findAll()).thenReturn(iterator);

        Iterator<Payment> retrievedIterator = paymentService.getAllPayments();

        assertEquals(iterator, retrievedIterator);
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testSetStatusInvalid() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, PaymentStatus.valueOf("INVALID_STATUS")));
        verify(paymentRepository, never()).update(any(Payment.class));
        verify(order, never()).setStatus(anyString());
    }

    @Test
    void testAddPaymentNullOrder(){
        assertThrows(IllegalArgumentException.class, () -> paymentService.addPayment(null, PaymentMethod.VOUCHER, paymentData));
        verify(paymentRepository, never()).create(any(Payment.class));
    }
}