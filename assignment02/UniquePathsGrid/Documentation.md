### Code Coverage

I wrote tests for two-dimensional grids (i.e., either rows or columns are 1),
for small grids and for a grid which is 100 in one direction.
That was enough for 100% code coverage.

Later on I ensured 100%% line coverage also with the checks for  pre- and postconditions.

### Designing Contracts

The class has no state, so no invariants had to be tested.

For the preconditions, the parameters both had to be in the range [1, 100].
If not the method throws an IllegalArgumentException,
as this is a meaningful exception for the user and ensures the method is used as intended.

The only postcondition is that the result cannot be negative.
I used assertions for this, as it's an internal implementation detail.

The other constraints in the ReadMe are not contracts.

### Testing Contracts

I wrote tests for the preconditions to see if they throw the correct exceptions.
For the postconditions, I found some legal arguments that violated them,
and wrote tests for them.

### Property-Based Testing

One property-based test was for the normal execution.
As calculating the outcome would be non-trivial,
I only tested that with valid inputs the method executes and the postconditions hold.

The other property-based test was to test invalid inputs.
There I tested that the method throws an exception for any invalid input
(i.e., any parameter does not adhere to the contract).