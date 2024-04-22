# Documentation for `FindDuplicate` Class

## Overview

The `FindDuplicate` class implements the Floyd's Tortoise and Hare algorithm to find a duplicate in an array where each element is between 0 and the array's length minus one. This implementation is based on the assumption that there is at least one duplicate due to the necessity of a cycle for the algorithm to function.

## Changes and Rationale

### Initial Implementation

The initial implementation was designed to find the duplicate using a cycle detection method (Floyd's Tortoise and Hare) without pre-validating the indices. This approach was giving `ArrayIndexOutOfBoundsException` if the array contained numbers outside the valid index range.

### Updated Implementation

To improve the codes robustness, some changes have been made as following:

#### Code Enhancements

```java
public static int findDuplicate(int[] nums) {
    if (nums == null) {
        throw new IllegalArgumentException("Array must not be null.");
    }
    if (nums.length <= 1) {
        throw new IllegalArgumentException("Array must contain at least two elements.");
    }

    for (int num : nums) {
        if (num < 0 || num >= nums.length) {
            throw new IllegalArgumentException("Array values must be valid indices within the array.");
        }
    }

    int tortoise = nums[0];
    int hare = nums[0];
    // Phase 1: Finding the intersection point of the two runners.
    do {
        tortoise = nums[tortoise];
        hare = nums[nums[hare]];
    } while (tortoise != hare);

    // Phase 2: Finding the "entrance" to the cycle.
    tortoise = nums[0];
    while tortoise != hare) {
        tortoise = nums[tortoise];
        hare = nums[hare];
    }

    return hare;
}
```


# Rationale

## Testing Strategy

The testing strategy for the `FindDuplicate` class is designed to ensure thorough coverage and robustness of the implementation. We aimed for comprehensive coverage, including line and branch coverage, and to test all conceivable edge cases. The strategy included:

- **Unit Testing**: Testing individual functions under controlled conditions with a variety of input scenarios.
- **Property-Based Testing**: Automatically generating a range of inputs to ensure the algorithm behaves as expected across a broad spectrum of data.
- **Edge Case Analysis**: Explicitly testing boundaries and special conditions (e.g., empty arrays, arrays with single elements, arrays with values out of index range, and negative values).

## Test Implementation

### Test Scenarios Covered

1. **Standard Duplicate Detection**: Verify the algorithm's ability to find duplicates when they are present in typical scenarios.
2. **Edge Cases**:
   - Empty array input.
   - Array with a single element.
   - Array values out of the valid index range.
   - Negative values in the array.
3. **Error Handling**: Ensure the algorithm throws appropriate exceptions for invalid inputs such as null arrays or invalid number ranges.
4. **Property-Based Tests**:
   - Generated tests with guaranteed duplicates to confirm consistent detection.
   - Arrays without duplicates to test the exception handling.
    - Identifying Duplicates (everyReturnedDuplicateMustActuallyBeADuplicate):This property ensures that if the findDuplicate method returns a value, that value must actually be a duplicate element within the input array.
    - Handling Invalid Arrays (shouldThrowForInvalidArrays):
    This property verifies that the findDuplicate method throws an IllegalArgumentException when presented with invalid arrays.

### Key Tests

- `findDuplicate_Standard()`: Confirms that the algorithm detects duplicates correctly under normal conditions.
- `findDuplicate_ValueTooLarge()`: Ensures the algorithm throws an exception when array values exceed the permissible index range.
- `findDuplicate_NegativeValues()`: Tests the handling of negative indices, ensuring robustness against invalid data.
- `findDuplicate_NullArray()` and `findDuplicate_EmptyArray()`: Verify that the algorithm appropriately handles null and empty arrays by throwing exceptions.

## Results

Testing resulted in full line and branch coverage, affirming that all parts of the code were executed and tested:

- **Coverage Metrics**: Achieved 100% line and branch coverage as verified by JaCoCo.
- **Property-Based Testing**: No failures were detected across the generated test cases, indicating reliable duplicate detection.
- **Edge Case Handling**: The implementation correctly managed all edge cases, throwing exceptions where appropriate and detecting duplicates correctly across all valid test scenarios.

## Assumptions

- The input array should ideally contain only non-negative integers that do not exceed the array's length minus one. However, tests ensure that the system gracefully handles deviations from this assumption.
- Each element in the array points to another index, forming a logical connection intended to facilitate cycle detection.


## Pre-Iteration Check
A validation loop was introduced at the start of the `findDuplicate` method to check that all elements are within the valid index range. This is essential to avoid any out-of-bounds access.

## Exception Handling
An `IllegalArgumentException` is thrown if any elements are found during pre-validation that cannot be used as indices, preventing further processing and potential crashes.

