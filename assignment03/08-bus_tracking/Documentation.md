# Real-Time Bus Tracking System

### A. Accuracy of Location Updates

The GPS and Map services were mocked as they needed mock implementations, while the notification service was essentially just a dummy.
Tests were written for one and multiple updates, null update and multiple buses driving at the same time.

I implemented the common objects and mocks as fields, which would be initialized (if necessary) by the `BeforeEach` setup method.
This reduces code duplication.

### B. Notification of Key Events

I tested the boudaries for reaching key locations, consisting of null location, no key location + one key location,
and multiple locations (some of which were key locations).
Because the notification text might change, I encapsulated the assertion in a separate method, so if it changes it only needs to be updated in one location.

### C. Response to GPS Signal Loss

I tested a single signal loss, repeated signal losses, as well as intermittent signal loss (goes back to working again).
Because the notification message was hard coded, I again moved the assertion to a spearate method for my tests to use.
By running the tests, I implicitly also test that no error is thrown.

### D. Comparison

In the current implementation with direct method calls users could get notified multiple times about reaching a key location if the location never actually changes between method calls. The same issue occurs with loss of GPS signal data. Overall direct method calls can result in a lot of duplication and inefficiencies.
