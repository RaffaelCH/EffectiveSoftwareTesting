package zest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PalindromeOneTest {

    @Test
    public void single_digit_is_palindrome() {
        assertTrue(PalindromeOne.isPalindrome(0));
        assertTrue(PalindromeOne.isPalindrome(1));
    }

    @Test
    public void multiple_digits_palindrome() {
        assertTrue(PalindromeOne.isPalindrome(22));
        assertTrue(PalindromeOne.isPalindrome(545));
        assertTrue(PalindromeOne.isPalindrome(1_000_001));
    }

    @Test
    public void multiple_digits_not_palindrome() {
        assertFalse(PalindromeOne.isPalindrome(10));
        assertFalse(PalindromeOne.isPalindrome(100));
        assertFalse(PalindromeOne.isPalindrome(999990));
    }

    @Test
    public void negative_number_not_palindrome() {
        assertFalse(PalindromeOne.isPalindrome(-1));
        assertFalse(PalindromeOne.isPalindrome(-10));
        assertFalse(PalindromeOne.isPalindrome(-1_000_001));
    }

    @Test
    public void test_boundaries() {
        assertFalse(PalindromeOne.isPalindrome(-2^20));
        assertFalse(PalindromeOne.isPalindrome(2^20 - 1));
    }
}