package zest;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PaymentProcessorTest {

    @Test
    public void Test_onTransactionComplete_NumberOfInvocations() {
        var transactionService = mock(TransactionService.class);
        var fraudDetectionService = mock(FraudDetectionService.class);

        var validPayment = new Transaction("1");
        var fraudulentPayment = new Transaction("2");

        when(fraudDetectionService.evaluateTransaction(validPayment)).thenReturn(true);
        when(fraudDetectionService.evaluateTransaction(fraudulentPayment)).thenReturn(false);

        var auditService = mock(AuditService.class);
        var eventPublisher = new EventPublisher();
        eventPublisher.subscribe(auditService);

        var paymentProcessor = new PaymentProcessor(eventPublisher, transactionService, fraudDetectionService);

        paymentProcessor.processPayment(validPayment);
        verify(auditService, times(1)).onTransactionComplete(any(Transaction.class));

        paymentProcessor.processPayment(fraudulentPayment);
        verify(auditService, times(1)).onTransactionComplete(any(Transaction.class));

        paymentProcessor.processPayment(validPayment);
        verify(auditService, times(2)).onTransactionComplete(any(Transaction.class));
    }
}