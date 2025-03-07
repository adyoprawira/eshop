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
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.SUCCESS, paymentData);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        assertEquals(PaymentStatus.PENDING, payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.REJECTED, paymentData);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.valueOf("INVALID_STATUS"), paymentData);
        });
    }

    @Test
    void testSetStatusToSuccess() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        payment.setStatus(PaymentStatus.REJECTED);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreatePaymentValidMethodVoucher() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        assertEquals(PaymentMethod.VOUCHER, payment.getMethod());
    }

    @Test
    void testCreatePaymentValidMethodBankTransfer() {
        payment = new Payment("1", PaymentMethod.BANK_TRANSFER, PaymentStatus.PENDING, paymentData);
        assertEquals(PaymentMethod.BANK_TRANSFER, payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            PaymentMethod.valueOf("INVALID_METHOD");
        });
    }

    @Test
    void testSetMethodToValidMethod() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        payment.setMethod(PaymentMethod.BANK_TRANSFER);
        assertEquals(PaymentMethod.BANK_TRANSFER, payment.getMethod());
    }

    @Test
    void testCreatePaymentValidPaymentData() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, data);
        assertEquals(data, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentNullPaymentData() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testSetPaymentDataValid() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, new HashMap<>());
        Map<String, String> newData = new HashMap<>();
        newData.put("bankName", "Example Bank");
        payment.setPaymentData(newData);
        assertEquals(newData, payment.getPaymentData());
    }

    @Test
    void testSetPaymentDataNull() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, new HashMap<>());
        payment.setPaymentData(null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testCreatePaymentValidId() {
        payment = new Payment("validId", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        assertEquals("validId", payment.getId());
    }

    @Test
    void testSetIdValid() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        payment.setId("newId");
        assertEquals("newId", payment.getId());
    }

    @Test
    void testCreatePaymentNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(null, PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        });
    }

    @Test
    void testCreatePaymentEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        });
    }

    @Test
    void testSetIdNull() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setId(null);
        });
    }

    @Test
    void testSetIdEmpty() {
        payment = new Payment("1", PaymentMethod.VOUCHER, PaymentStatus.PENDING, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setId("");
        });
    }
}