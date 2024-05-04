package zest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PaymentProcessorTest {

    private final Transaction validTransaction = new Transaction("1");
    private final Transaction fraudulentTransaction = new Transaction("2");

    @Test
    public void Test_onTransactionComplete_NumberOfInvocations_OnlyValid() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(validTransaction);
        for (var auditService : auditServices) {
            verify(auditService, times(1)).onTransactionComplete(any(Transaction.class));
        }
    }

    @Test
    public void Test_onTransactionComplete_NumberOfInvocations_OnlyFraudulent() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(fraudulentTransaction);
        for (var auditService : auditServices) {
            verify(auditService, times(0)).onTransactionComplete(any(Transaction.class));
        }
    }

    @Test
    public void Test_onTransactionComplete_NumberOfInvocations_ValidAndFraudulent() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(validTransaction);
        for (var auditService : auditServices) {
            verify(auditService, times(1)).onTransactionComplete(any(Transaction.class));
        }

        paymentProcessor.processPayment(fraudulentTransaction);
        for (var auditService : auditServices) {
            verify(auditService, times(1)).onTransactionComplete(any(Transaction.class));
        }

        paymentProcessor.processPayment(validTransaction);
        for (var auditService : auditServices) {
            verify(auditService, times(2)).onTransactionComplete(any(Transaction.class));
        }
    }

    @Test
    public void Test_onTransactionComplete_ArgumentCaptor_OnlyValid() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(validTransaction);

        for (var auditService : auditServices) {
            var captor = ArgumentCaptor.forClass(Transaction.class);
            verify(auditService).onTransactionComplete(captor.capture());
            Transaction publishedTransaction = captor.getValue();
            assertEquals(publishedTransaction, validTransaction);
        }
    }

    @Test
    public void Test_onTransactionComplete_ArgumentCaptor_ValidAndFraudulent() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(validTransaction);
        for (var auditService : auditServices) {
            ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
            verify(auditService).onTransactionComplete(captor.capture());
            Transaction publishedTransaction = captor.getValue();
            assertEquals(publishedTransaction, validTransaction);
        }

        paymentProcessor.processPayment(fraudulentTransaction);
        for (var auditService : auditServices) {
            var captor = ArgumentCaptor.forClass(Transaction.class);
            verify(auditService, times(1)).onTransactionComplete(captor.capture());
            var publishedTransactions = captor.getAllValues();
            assertEquals(publishedTransactions.get(0), validTransaction);
        }

        paymentProcessor.processPayment(validTransaction);
        for (var auditService : auditServices) {
            var captor = ArgumentCaptor.forClass(Transaction.class);
            verify(auditService, times(2)).onTransactionComplete(captor.capture());
            List<Transaction> publishedTransaction = captor.getAllValues();
            assertEquals(publishedTransaction.get(0), validTransaction);
            assertEquals(publishedTransaction.get(1), validTransaction);
        }
    }

    @Test
    public void Test_onTransactionComplete_IncreasedObservability_OnlyValid() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        var processedPayments = paymentProcessor.processPayment(validTransaction);
        assertEquals(processedPayments.size(), 1);
    }

    @Test
    public void Test_onTransactionComplete_IncreasedObservability_OnlyFraudulent() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(fraudulentTransaction);
        var processedPayments = paymentProcessor.processPayment(fraudulentTransaction);
        assertTrue(processedPayments.isEmpty());
    }

    @Test
    public void Test_onTransactionComplete_IncreasedObservability_ValidAndFraudulent() {
        var auditServices = new ArrayList<AuditService>();
        auditServices.add(mock(AuditService.class));
        auditServices.add(mock(AuditService.class));
        var paymentProcessor = SetupPaymentProcessorWithAuditService(auditServices);

        paymentProcessor.processPayment(validTransaction);
        var processedPayments = paymentProcessor.processPayment(validTransaction);
        assertEquals(processedPayments.size(), 1);
        assertEquals(processedPayments.get(0), validTransaction);

        paymentProcessor.processPayment(fraudulentTransaction);
        processedPayments = paymentProcessor.processPayment(fraudulentTransaction);
        assertTrue(processedPayments.isEmpty());

        paymentProcessor.processPayment(validTransaction);
        processedPayments = paymentProcessor.processPayment(validTransaction);
        assertEquals(processedPayments.size(), 1);
        assertEquals(processedPayments.get(0), validTransaction);
    }

    @Test
    public void Test_publishTransactionComplete_NumberOfInvocations_OnlyValid() {
        var eventPublisher = mock(EventPublisher.class);
        var paymentProcessor = SetupPaymentProcessorWithEventPublisher(eventPublisher);

        paymentProcessor.processPayment(validTransaction);
        verify(eventPublisher, times(1)).publishTransactionComplete(any(Transaction.class));
    }

    @Test
    public void Test_publishTransactionComplete_NumberOfInvocations_OnlyFraudulent() {
        var eventPublisher = mock(EventPublisher.class);
        var paymentProcessor = SetupPaymentProcessorWithEventPublisher(eventPublisher);

        paymentProcessor.processPayment(fraudulentTransaction);
        verify(eventPublisher, never()).publishTransactionComplete(any(Transaction.class));
    }

    @Test
    public void Test_publishTransactionComplete_NumberOfInvocations_ValidAndFraudulent() {
        var eventPublisher = mock(EventPublisher.class);
        var paymentProcessor = SetupPaymentProcessorWithEventPublisher(eventPublisher);

        paymentProcessor.processPayment(validTransaction);
        verify(eventPublisher, times(1)).publishTransactionComplete(any(Transaction.class));
        verify(eventPublisher, times(1)).publishTransactionComplete(validTransaction);

        paymentProcessor.processPayment(fraudulentTransaction);
        verify(eventPublisher, times(1)).publishTransactionComplete(any(Transaction.class));
        verify(eventPublisher, never()).publishTransactionComplete(fraudulentTransaction);

        paymentProcessor.processPayment(validTransaction);
        verify(eventPublisher, times(2)).publishTransactionComplete(any(Transaction.class));
        verify(eventPublisher, times(2)).publishTransactionComplete(validTransaction);
    }

    private PaymentProcessor SetupPaymentProcessorWithAuditService(List<AuditService> auditServices) {
        var transactionService = mock(TransactionService.class);
        var fraudDetectionService = mock(FraudDetectionService.class);

        when(fraudDetectionService.evaluateTransaction(validTransaction)).thenReturn(true);
        when(fraudDetectionService.evaluateTransaction(fraudulentTransaction)).thenReturn(false);

        var eventPublisher = new EventPublisher();

        for (var auditService : auditServices)
            eventPublisher.subscribe(auditService);

        return new PaymentProcessor(eventPublisher, transactionService, fraudDetectionService);
    }

    private PaymentProcessor SetupPaymentProcessorWithEventPublisher(EventPublisher eventPublisher) {
        var transactionService = mock(TransactionService.class);
        var fraudDetectionService = mock(FraudDetectionService.class);

        when(fraudDetectionService.evaluateTransaction(validTransaction)).thenReturn(true);
        when(fraudDetectionService.evaluateTransaction(fraudulentTransaction)).thenReturn(false);

        return new PaymentProcessor(eventPublisher, transactionService, fraudDetectionService);
    }
}