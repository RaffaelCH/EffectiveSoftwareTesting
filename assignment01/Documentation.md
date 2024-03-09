## atoi

## combination_sum

## frac2dec

## generate_parentheses

## maximum_subarray

## median_of_arrays

## needle_in_hay

## palindrome
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