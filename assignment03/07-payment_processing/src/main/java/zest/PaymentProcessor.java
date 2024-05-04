package zest;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessor {
    private EventPublisher eventPublisher;
    private TransactionService transactionService;
    private FraudDetectionService fraudDetectionService;

    public PaymentProcessor(EventPublisher publisher, TransactionService service, FraudDetectionService fraudService) {
        this.eventPublisher = publisher;
        this.transactionService = service;
        this.fraudDetectionService = fraudService;
    }

    public List<Transaction> processPayment(Transaction transaction) {
        var processedPayments = new ArrayList<Transaction>();
        if (fraudDetectionService.evaluateTransaction(transaction)) {
            transactionService.processTransaction(transaction);
            eventPublisher.publishTransactionComplete(transaction);
            processedPayments.add(transaction);
        }
        return processedPayments;
    }
}
