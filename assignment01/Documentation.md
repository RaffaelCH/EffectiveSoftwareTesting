## atoi

## combination_sum

## frac2dec

## generate_parentheses

## maximum_subarray

## median_of_arrays

## needle_in_hay

First I read the specs, and created a simple test case to explore how it works.

Then I identified the partitions, some of which were already described in the specification (any string null, both inputs empty). I then combined the partitions for the inputs to create test cases. If one of the inputs was exceptional (null, empty string), I wrote a single test for it.

After that I wrote tests for the case that the needle is or is not in the haystack. For this I took into account the partitions I created (in this case 1 char string, longer string) and added other test cases based on instinct (needle present multiple times).

I found that if the haystack was not an empty String but the needle was, an exception was thrown. I fixed this by adding another guard clause for this test.

## palindrome
