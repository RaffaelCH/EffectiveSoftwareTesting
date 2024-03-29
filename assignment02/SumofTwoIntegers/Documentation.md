### Code Coverage

I wrote tests for positive, negative and mixed arguments,
and for each of these scenarios I tested the boundaries (0, min and max).
This resulted in 100% code coverage.

### Designing Contracts

The only contract is that both arguments are in the 32bit integer range.
This precondition is already implicitly enforced by the Java compiler.
As there is no state, there are also no invariants.

I added an assert clause that verifies that the outcome is the same as with the + operator,
by storing the param values to verify the result using them.

There is no boundary specified for the result, which means it is not defined what happens for under/overflows.
I added tests that verify that getSum behaves like the + operator,
as well as an alternative version returning a long which prevents this issue completely.

### Testing Contracts

As the contract is already enforced by the compiler,
the existing tests already verify the preconditions.
The post-condition cannot fail based on the current implementation.
And if the implementation is changed, the existing tests should be enough.