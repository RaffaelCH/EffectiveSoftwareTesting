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

#### Specification-Based Testing

After reading the description, I split up the test into partitions.
The first partition was from the lower bound (-2^20) to -1,
and the second was from 0 to upper bound (2^20).

Then I further split them up into single digit and multiple digit partitions.

For the single digit success case, I tested the on point (0) and another case.
For the multiple digits success case, I tested an even and an uneven number of digits.

For the negative numbers, I tested the negatives of palindromes (taken from above),
and a negative number whose positive also wasn't a palindrome.

I then also tested positive numbers that weren't palindromes.

Then I wrote tests for the boundaries.

Finally, I added tests for palindromes that were close to the boundaries
(i.e., same number of digits) due to personal judgement.

I used the same tests for both palindrome implementations.
For PalindromeTwo, I discovered that the test case with 0 failed.
I fixed it by adding another clause to test for single digits.

#### Structural Testing

For PalindromeOne I already achieved 100% coverage.

For PalindromeTwo I had 90% coverage. I added a test for 999 to test the long conditional,
as this was a path returning true I had not covered but found important.
There remained two cases, but as the condition was already covered
and I didn't find a test case for them within a reasonable time frame, I skipped them.
