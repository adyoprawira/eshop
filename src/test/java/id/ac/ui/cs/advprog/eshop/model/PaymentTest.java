import id.ac.ui.cs.advprog.eshop.model.Payment;
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
        payment = new Payment("1", "VOUCHER", "SUCCESS", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        payment = new Payment("1", "VOUCHER", "REJECTED", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", "VOUCHER", "INVALID_STATUS", paymentData);
        });
    }

    @Test
    void testSetStatusToSuccess() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testCreatePaymentValidMethodVoucher() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        assertEquals("VOUCHER", payment.getMethod());
    }

    @Test
    void testCreatePaymentValidMethodBankTransfer() {
        payment = new Payment("1", "BANK_TRANSFER", "PENDING", paymentData);
        assertEquals("BANK_TRANSFER", payment.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1", "INVALID_METHOD", "PENDING", paymentData);
        });
    }

    @Test
    void testSetMethodToValidMethod() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        payment.setMethod("BANK_TRANSFER");
        assertEquals("BANK_TRANSFER", payment.getMethod());
    }

    @Test
    void testSetMethodToInvalidMethod() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setMethod("MEOW"));
    }

    @Test
    void testCreatePaymentValidPaymentData() {
        Map<String, String> data = new HashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("1", "VOUCHER", "PENDING", data);
        assertEquals(data, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentNullPaymentData() {
        payment = new Payment("1", "VOUCHER", "PENDING", null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testSetPaymentDataValid() {
        payment = new Payment("1", "VOUCHER", "PENDING", new HashMap<>());
        Map<String, String> newData = new HashMap<>();
        newData.put("bankName", "Example Bank");
        payment.setPaymentData(newData);
        assertEquals(newData, payment.getPaymentData());
    }

    @Test
    void testSetPaymentDataNull() {
        payment = new Payment("1", "VOUCHER", "PENDING", new HashMap<>());
        payment.setPaymentData(null);
        assertNull(payment.getPaymentData());
    }

    @Test
    void testCreatePaymentValidId() {
        payment = new Payment("validId", "VOUCHER", "PENDING", paymentData);
        assertEquals("validId", payment.getId());
    }

    @Test
    void testSetIdValid() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        payment.setId("newId");
        assertEquals("newId", payment.getId());
    }

    @Test
    void testCreatePaymentNullId() {
        payment = new Payment(null, "VOUCHER", "PENDING", paymentData);
        assertNull(payment.getId());
    }

    @Test
    void testCreatePaymentEmptyId() {
        payment = new Payment("", "VOUCHER", "PENDING", paymentData);
        assertEquals("", payment.getId());
    }

    @Test
    void testSetIdNull() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        payment.setId(null);
        assertNull(payment.getId());
    }

    @Test
    void testSetIdEmpty() {
        payment = new Payment("1", "VOUCHER", "PENDING", paymentData);
        payment.setId("");
        assertEquals("", payment.getId());
    }
}