package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    private Payment payment;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentValidStatus() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), paymentData);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", PaymentMethod.VOUCHER.getValue(), "INVALID_STATUS", paymentData);
        });
    }

    @Test
    void testSetStatusToSuccess() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testCreatePaymentValidMethodVoucher() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertEquals(PaymentMethod.VOUCHER, payment.getMethod());
    }

    @Test
    void testCreatePaymentValidMethodBankTransfer() {
        payment = new Payment("1", PaymentMethod.BANK_TRANSFER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertEquals(PaymentMethod.BANK_TRANSFER, payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", "INVALID_METHOD", PaymentStatus.PENDING.getValue(), paymentData);
        });
    }

    @Test
    void testSetMethodToValidMethod() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        payment.setMethod(PaymentMethod.BANK_TRANSFER.getValue());
        assertEquals(PaymentMethod.BANK_TRANSFER, payment.getMethod());
    }

    @Test
    void testSetMethodToInvalidMethod() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setMethod("MEOW"));
    }

    @Test
    void testCreatePaymentValidPaymentData() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), data);
        assertEquals(data, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentNullPaymentData() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testSetPaymentDataValid() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), new HashMap<>());
        Map<String, String> newData = new HashMap<>();
        newData.put("bankName", "Example Bank");
        payment.setPaymentData(newData);
        assertEquals(newData, payment.getPaymentData());
    }

    @Test
    void testSetPaymentDataNull() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), new HashMap<>());
        payment.setPaymentData(null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testCreatePaymentValidId() {
        payment = new Payment("validId", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertEquals("validId", payment.getId());
    }

    @Test
    void testSetIdValid() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        payment.setId("newId");
        assertEquals("newId", payment.getId());
    }

    @Test
    void testCreatePaymentNullId() {
        payment = new Payment(null, PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertNull(payment.getId());
    }

    @Test
    void testCreatePaymentEmptyId() {
        payment = new Payment("", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        assertEquals("", payment.getId());
    }

    @Test
    void testSetIdNull() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        payment.setId(null);
        assertNull(payment.getId());
    }

    @Test
    void testSetIdEmpty() {
        payment = new Payment("1", PaymentMethod.VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), paymentData);
        payment.setId("");
        assertEquals("", payment.getId());
    }
}