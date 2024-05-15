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
<!--- TODO --->
We implemented...

<!--- Implement tests using mocks for `NotificationService` and `LogService` to verify if the services are called correctly. --->

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
<!--- TODO --->

<!--- Simulate failures in notification and logging. 
Ensure the ticket creation process is resilient and completed successfully despite these challenges. --->

## Automate the Test Cases
<!--- TODO --->

<!--- Automate the test cases using the JUnit5 plugin in the `src/test/java/zest/` folder. --->
