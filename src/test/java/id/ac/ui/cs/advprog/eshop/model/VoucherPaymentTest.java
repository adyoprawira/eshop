package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class VoucherPaymentTest {

    private Order order;
    private VoucherPayment voucherPayment;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        voucherPayment = new VoucherPayment("1", PaymentStatus.PENDING, "ESHOP1234");
    }

    @Test
    void testVoucherPaymentConstructor() {
        assertEquals("1", voucherPayment.getId());
        assertEquals(PaymentStatus.PENDING, voucherPayment.getStatus());
        assertEquals("ESHOP1234", voucherPayment.getVoucherCode());
        assertEquals(PaymentMethod.VOUCHER, voucherPayment.getMethod());
    }

    @Test
    void testGetPaymentData() {
        assertNull(voucherPayment.getPaymentData());
    }

    @Test
    void testSetPaymentData() {
        // Since setPaymentData is a no-op, just call it to ensure coverage
        voucherPayment.setPaymentData(null);
        assertNull(voucherPayment.getPaymentData()); // Still null
    }

    @Test
    void testSetStatus() {
        voucherPayment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, voucherPayment.getStatus());
    }

    @Test
    void testSetMethod() {
        voucherPayment.setMethod(PaymentMethod.BANK_TRANSFER);
        assertEquals(PaymentMethod.BANK_TRANSFER, voucherPayment.getMethod());
    }

    @Test
    void testSetId() {
        voucherPayment.setId("2");
        assertEquals("2", voucherPayment.getId());
    }

    @Test
    void testSetOrder() {
        voucherPayment.setOrder(order);
        assertEquals(order, voucherPayment.getOrder());
    }

    @Test
    void testSetVoucherCode() {
        voucherPayment.setVoucherCode("NEWCODE5678");
        assertEquals("NEWCODE5678", voucherPayment.getVoucherCode());
    }
}