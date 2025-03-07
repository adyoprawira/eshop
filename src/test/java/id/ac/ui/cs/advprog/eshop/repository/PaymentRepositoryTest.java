package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentRepositoryTest {

    private PaymentRepository paymentRepository;
    private Payment payment1;
    private Payment payment2;
    private Payment payment3;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payment1 = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Example Bank");
        payment2 = new Payment("2", PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData2);

        Map<String, String> paymentData3 = new HashMap<>();
        paymentData3.put("voucherCode", "ESHOP9876XYZ5432");
        payment3 = new Payment("3", PaymentMethod.VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData3);
    }

    @Test
    void testCreatePaymentAndCheckId() {
        Payment createdPayment = paymentRepository.create(payment1);
        assertEquals("1", createdPayment.getId());
    }

    @Test
    void testCreateMultiplePaymentsAndCheckOrder() {
        paymentRepository.create(payment1);
        paymentRepository.create(payment2);
        paymentRepository.create(payment3);

        Iterator<Payment> paymentIterator = paymentRepository.findAll();
        assertEquals(payment1, paymentIterator.next());
        assertEquals(payment2, paymentIterator.next());
        assertEquals(payment3, paymentIterator.next());
        assertFalse(paymentIterator.hasNext());
    }

    @Test
    void testUpdatePaymentMethod() {
        paymentRepository.create(payment1);
        payment1.setMethod(PaymentMethod.BANK_TRANSFER.getValue());
        Payment updatedPayment = paymentRepository.update(payment1);
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), updatedPayment.getMethod());

        Payment retrievedPayment = paymentRepository.findById("1");
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), retrievedPayment.getMethod());
    }

    @Test
    void testUpdatePaymentData() {
        paymentRepository.create(payment1);
        Map<String, String> newData = new HashMap<>();
        newData.put("voucherCode", "NEWVOUCHER12345678");
        payment1.setPaymentData(newData);
        Payment updatedPayment = paymentRepository.update(payment1);
        assertEquals(newData, updatedPayment.getPaymentData());

        Payment retrievedPayment = paymentRepository.findById("1");
        assertEquals(newData, retrievedPayment.getPaymentData());
    }

    @Test
    void testUpdatePaymentWithNullId() {
        paymentRepository.create(payment1);
        payment1.setId(null);
        Payment updatedPayment = paymentRepository.update(payment1);
        assertNull(updatedPayment);
    }

    @Test
    void testUpdatePaymentWithEmptyId() {
        paymentRepository.create(payment1);
        payment1.setId("");
        Payment updatedPayment = paymentRepository.update(payment1);
        assertNull(updatedPayment);
    }
}