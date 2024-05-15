# Task 05: Ticket System
## Unit Tests for createTicket
### Use of Doubles for NotificationService and LogService
Using doubles for external dependencies often is a good practice in unit testing,
as external dependencies can be slow, unreliable, or expensive to use in tests.
Tests should be fast, cheap to run and not flaky 
(= fail sometimes and sometimes not, without a code production or test code change).

*Please note that we created a `zest` package for this task in `src/java/` and `src/test/java/`, such that the structure matches the described structure in the `README.md` file (`src/test/java/zest/`).*
#### Identify Dependencies
In the `createTicket` method, two external dependencies are used. Both should be mocked in the tests. Here is why:

The first external dependency is `LogService`, which logs the creation of a ticket.
In a real system, the logs may be saved to files. The access to files can for example be slow, 
depending on the machine and file size. Further, the file system can be full, access to the file is missing,
or the file is locked by another process or user. Further, there needs to be a cleanup afterwards. All these factors can make the test slow, unreliable and flaky.
Further, when tests are run, the files can be in different states (can have different content before a test runs; must ensure that it is the exact same), 
which makes tests flaky, too.
This is why the `LogService` should be mocked in the tests.

The second external dependency is `NotificationService`, which notifies the customer about the just created ticket.
The service suggests that there will be a notification which is sent via email.
Sending emails can be, similar to the file access described above, slow and unreliable.
The communication with the email server can sometimes take a long time, the email server can be down,
or there is a communication problem between the test machine and the email server.
Our unit tests then sometimes fail, e.g. because the external email server is down, sometimes work,
or sometimes take a long time to run, when the internet speed is slow.
As unit tests must be fast, reliable and not flaky, the `NotificationService` should be mocked in the tests.
#### Test Doubles Usage
We implemented tests that mock the `NotifiactionService` and the `LogService`. We also decided to mock the `TicketRepository` class, as it could be another external dependency that could be slow or otherwise unreliable.
All three classes are mocked using Mockito. The tests are in the `TicketManagerTest` class in the `src/test/java/zest/` folder.
We also need a `@BeforeEach` method that initializes the `TicketManager` class (which is not mocked, but uses mocks).

The **first** test is a parametrized test, which tests the `createTicket` method.
We define a CsvSource with the following values:
- an input that could happen likely in a real system
- a very short input
- a very long input
- an input with special characters

We use the doNothing() method of Mockito to mock the `LogService` and `NotificationService` classes.
Then, we use the `createTicket` method of the `TicketManager` class to create a ticket.
We verify that the `LogService` and `NotificationService` classes are called correctly.

The **second** test tests a failure in the `NotificationService` class.
To do so, we use the `doThrow` method of Mockito to throw a `RuntimeException` when the `notifyCustomer` method is called.
Then, we check that the `createTicket` method of the `TicketManager` throws the `RuntimeException` (we compare its exception messages).
Now, we also verify that `logTicketCreation` and `notifyCustomer` are called exactly once.

The **third** test tests a failure in the `LogService` class.
It works analogously to the second test, but with the `logTicketCreation` method.
We further verify that the `notifyCustomer` method is never called.

### Disadvantages of Using Doubles in Your Tests
There are disadvantages in using doubles in this scenario.
When using doubles, the real connection to and usage of the external dependencies is not tested.
This means that we may not detect problems with the real external dependencies.
In our case, we may not detect that the `LogService` does not write to the file system correctly,
as we replaced it with a mock. We also may not detect that the `NotificationService` does not send emails correctly,
as it does not actually communicate with a real email server which sends the emails.

We can prevent this by using integration tests, which test the real connection to the external dependencies. 
These integration tests then can be run less often than unit tests, as they take longer and are more expensive to run,
for example before merging a feature branch into the main branch, or before a release.

Another disadvantage is that the tests can become more complex and harder to understand.
When using doubles, we have to write the mock objects, set up the mocks, and verify the mocks.

### Handling of Failures in Notification and Logging
In our tests, we simulated failures in the `NotificationService` and `LogService` classes.
We did this by throwing a `RuntimeException` when the `notifyCustomer` or `logTicketCreation` methods are called.
We then checked that the `createTicket` method of the `TicketManager` class throws the `RuntimeException`.
The code is resilient, as it catches the exception and does not crash.

The code is not very resilient against bugs.
For example, the `createTicket` method doesn't do any error handling. 
We could surround the calls to external dependencies with a try-catch block and handle exceptions.

Further, it is good for testability that the external code is passed via the constructor.
Instead of the NotificationService and LogService, we could pass a NotificationService and LogService interface to the `createTicket` method as well, not having to use mocks, but pass our own classes to the method.