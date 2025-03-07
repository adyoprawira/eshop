package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentMethod;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.service.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoucherPaymentTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;
    private VoucherPayment voucherPayment;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        voucherPayment = new VoucherPayment("1", PaymentStatus.PENDING, "ESHOP1234");
    }

    @Test
    void testAddVoucherPayment() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(voucherPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.VOUCHER, null);
        assertNotNull(createdPayment);
        assertInstanceOf(VoucherPayment.class, createdPayment);
        VoucherPayment createdVoucherPayment = (VoucherPayment) createdPayment;
        assertEquals("ESHOP1234", createdVoucherPayment.getVoucherCode());
        verify(paymentRepository).create(any(Payment.class));
    }

    @Test
    void testSetVoucherPaymentStatusSuccess() {
        when(paymentRepository.update(any(Payment.class))).thenReturn(voucherPayment);
        Payment updatedPayment = paymentService.setStatus(voucherPayment, PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, updatedPayment.getStatus());
        verify(paymentRepository).update(any(Payment.class));
    }

    @Test
    void testSetVoucherPaymentStatusRejected() {
        when(paymentRepository.update(any(Payment.class))).thenReturn(voucherPayment);
        Payment updatedPayment = paymentService.setStatus(voucherPayment, PaymentStatus.REJECTED);
        assertEquals(PaymentStatus.REJECTED, updatedPayment.getStatus());
        verify(paymentRepository).update(any(Payment.class));
    }

    @Test
    void testVoucherPaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(voucherPayment, PaymentStatus.valueOf("INVALID_STATUS")));
        verify(paymentRepository, never()).update(any(Payment.class));
        verify(order, never()).setStatus(anyString());
    }

    @Test
    void testAddVoucherPaymentNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.addPayment(null, PaymentMethod.VOUCHER, null));
        verify(paymentRepository, never()).create(any(Payment.class));
    }

    @Test
    void testVoucherPaymentMethod() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(voucherPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.VOUCHER, null);
        assertEquals(PaymentMethod.VOUCHER, createdPayment.getMethod());
    }

    @Test
    void testVoucherPaymentDataNull() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(voucherPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.VOUCHER, null);
        assertNull(createdPayment.getPaymentData());
    }

    @Test
    void testVoucherPaymentVoucherCode(){
        when(paymentRepository.create(any(Payment.class))).thenReturn(voucherPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.VOUCHER, null);
        assertInstanceOf(VoucherPayment.class, createdPayment);
        VoucherPayment createdVoucherPayment = (VoucherPayment) createdPayment;
        assertEquals("ESHOP1234", createdVoucherPayment.getVoucherCode());
    }
}