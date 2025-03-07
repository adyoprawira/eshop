package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TransferPaymentTest {

    private Order order;
    private TransferPayment transferPayment;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        transferPayment = new TransferPayment("1", PaymentStatus.PENDING, "Example Bank", "1234567890");
    }

    @Test
    void testTransferPaymentConstructor() {
        assertEquals("1", transferPayment.getId());
        assertEquals(PaymentStatus.PENDING, transferPayment.getStatus());
        assertEquals("Example Bank", transferPayment.getBankName());
        assertEquals("1234567890", transferPayment.getAccountNumber());
        assertEquals(PaymentMethod.BANK_TRANSFER, transferPayment.getMethod());
    }

    @Test
    void testGetPaymentData() {
        Map<String, String> data = transferPayment.getPaymentData();
        assertEquals("Example Bank", data.get("bankName"));
        assertEquals("1234567890", data.get("accountNumber"));
    }

    @Test
    void testSetPaymentDataValid() {
        Map<String, String> newData = new HashMap<>();
        newData.put("bankName", "New Bank");
        newData.put("accountNumber", "9876543210");
        transferPayment.setPaymentData(newData);
        assertEquals("New Bank", transferPayment.getBankName());
        assertEquals("9876543210", transferPayment.getAccountNumber());
    }

    @Test
    void testSetPaymentDataNull() {
        transferPayment.setPaymentData(null);
        assertEquals("Example Bank", transferPayment.getBankName()); // Should keep original values
        assertEquals("1234567890", transferPayment.getAccountNumber());
    }

    @Test
    void testSetStatus() {
        transferPayment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, transferPayment.getStatus());
    }

    @Test
    void testSetMethod() {
        transferPayment.setMethod(PaymentMethod.VOUCHER);
        assertEquals(PaymentMethod.VOUCHER, transferPayment.getMethod());
    }

    @Test
    void testSetId() {
        transferPayment.setId("2");
        assertEquals("2", transferPayment.getId());
    }

    @Test
    void testSetOrder() {
        transferPayment.setOrder(order);
        assertEquals(order, transferPayment.getOrder());
    }

    @Test
    void testSetBankName() {
        transferPayment.setBankName("New Bank Name");
        assertEquals("New Bank Name", transferPayment.getBankName());
    }

    @Test
    void testSetAccountNumber() {
        transferPayment.setAccountNumber("0987654321");
        assertEquals("0987654321", transferPayment.getAccountNumber());
    }
}