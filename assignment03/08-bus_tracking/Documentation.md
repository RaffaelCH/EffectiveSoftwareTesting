# Real-Time Bus Tracking System

### A. Accuracy of Location Updates

The GPS and Map services were mocked as they needed mock implementations, while the notification service was only a dummy.
Tests were written for one and multiple updates, null update and multiple buses driving at the same time.

I implemented the common objects and mocks as fields, which would be initialized (if necessary) by the `BeforeEach` setup method.
This reduces code duplication.

### B. Notification of Key Events

I tested the boudaries for reaching key locations, consisting of null location, no key location + one key location,
and multiple locations (some of which were key locations).
Because the notification text might change, I encapsulated the assertion in a separate method, so if it changes it only needs to be updated in one location.
