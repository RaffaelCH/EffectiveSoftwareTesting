# PaymentProcessor Tests

## Setup

Since it was missing, I added a `Transaction` class with one read-only field transactionId.
This is helpful to identify individual transactions and defining which are valid.

### A. Number of Invocations

I created a stub for the transactionService, since I just needed an object to pass to PaymentProcessor.
Two transactions were created to test valid and fraudulent payments.
Since two types of transactions can occur (valid and fraudulent),
I set up the fraudDetectionService mock to differentiate between them, although I could have also used a fake.

The test uses mock(s) of the auditService to test how often onTransactionComplete is called.
I wrote tests for valid and fraudulent transactions in isolation, and combinations of them,
with both a single and multiple audit services.

### B. Content of invocations—`ArgumentCaptor`

For verifying the arguments I only wrote tests for when valid transactions were captured,
since no arguments could be captured if a method was not called.

### C. Content of invocations—Increasing observability

To increase the obervability I refactored `PaymentProcessor` to return the processed transactions.
I could then write assertions on these transactions.

### D. Comparison

Using ArgumentCaptors requires more code, and is more limiting in what it can do
(e.g., hard/impossible to verify a method did not get called).
Having a return argument might also be useful in case the using code wants to do some kind of verification.

### publishTransactionComplete Unit Tests

As the tests mentioned above don't test `PaymentProcessor` in isolation (strictly speaking),
I also wrote some tests where I mocked EventPublisher to test how `publishTransactionComplete` gets called.
