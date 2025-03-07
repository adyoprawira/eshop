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
class TransferPaymentTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;
    private TransferPayment transferPayment;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        transferPayment = new TransferPayment("1", PaymentStatus.PENDING, "Example Bank", "1234567890");
    }

    @Test
    void testAddTransferPayment() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(transferPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER, transferPayment.getPaymentData());
        assertNotNull(createdPayment);
        assertInstanceOf(TransferPayment.class, createdPayment);
        TransferPayment createdTransferPayment = (TransferPayment) createdPayment;
        assertEquals("Example Bank", createdTransferPayment.getBankName());
        assertEquals("1234567890", createdTransferPayment.getAccountNumber());
        verify(paymentRepository).create(any(Payment.class));
    }

    @Test
    void testSetTransferPaymentStatusSuccess() {
        when(paymentRepository.update(any(Payment.class))).thenReturn(transferPayment);
        Payment updatedPayment = paymentService.setStatus(transferPayment, PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, updatedPayment.getStatus());
        verify(paymentRepository).update(any(Payment.class));
    }

    @Test
    void testSetTransferPaymentStatusRejected() {
        when(paymentRepository.update(any(Payment.class))).thenReturn(transferPayment);
        Payment updatedPayment = paymentService.setStatus(transferPayment, PaymentStatus.REJECTED);
        assertEquals(PaymentStatus.REJECTED, updatedPayment.getStatus());
        verify(paymentRepository).update(any(Payment.class));
    }

    @Test
    void testTransferPaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(transferPayment, PaymentStatus.valueOf("INVALID_STATUS")));
        verify(paymentRepository, never()).update(any(Payment.class));
        verify(order, never()).setStatus(anyString());
    }

    @Test
    void testAddTransferPaymentNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> paymentService.addPayment(null, PaymentMethod.BANK_TRANSFER, transferPayment.getPaymentData()));
        verify(paymentRepository, never()).create(any(Payment.class));
    }

    @Test
    void testTransferPaymentMethod() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(transferPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER, transferPayment.getPaymentData());
        assertEquals(PaymentMethod.BANK_TRANSFER, createdPayment.getMethod());
    }

    @Test
    void testTransferPaymentData() {
        when(paymentRepository.create(any(Payment.class))).thenReturn(transferPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER, transferPayment.getPaymentData());
        assertEquals(transferPayment.getPaymentData(), createdPayment.getPaymentData());
    }

    @Test
    void testTransferPaymentBankName(){
        when(paymentRepository.create(any(Payment.class))).thenReturn(transferPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER, transferPayment.getPaymentData());
        assertInstanceOf(TransferPayment.class, createdPayment);
        TransferPayment createdTransferPayment = (TransferPayment) createdPayment;
        assertEquals("Example Bank", createdTransferPayment.getBankName());
    }

    @Test
    void testTransferPaymentAccountNumber(){
        when(paymentRepository.create(any(Payment.class))).thenReturn(transferPayment);
        Payment createdPayment = paymentService.addPayment(order, PaymentMethod.BANK_TRANSFER, transferPayment.getPaymentData());
        assertInstanceOf(TransferPayment.class, createdPayment);
        TransferPayment createdTransferPayment = (TransferPayment) createdPayment;
        assertEquals("1234567890", createdTransferPayment.getAccountNumber());
    }
}