package zest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class PalindromeTwoTest {
    @Test
    public void single_digit_is_palindrome() {
        assertTrue(PalindromeTwo.isPalindrome(0));
        assertTrue(PalindromeTwo.isPalindrome(9));
    }

    @Test
    public void multiple_digits_palindrome() {
        assertTrue(PalindromeTwo.isPalindrome(22));
        assertTrue(PalindromeTwo.isPalindrome(999));
        assertTrue(PalindromeTwo.isPalindrome(1_000_001));
    }

    @Test
    public void multiple_digits_not_palindrome() {
        assertFalse(PalindromeTwo.isPalindrome(10));
        assertFalse(PalindromeTwo.isPalindrome(87));
        assertFalse(PalindromeTwo.isPalindrome(209));
        assertFalse(PalindromeTwo.isPalindrome(999990));
    }

    @Test
    public void negative_number_not_palindrome() {
        assertFalse(PalindromeTwo.isPalindrome(-1));
        assertFalse(PalindromeTwo.isPalindrome(-10));
        assertFalse(PalindromeTwo.isPalindrome(-1_000_001));
    }

    @Test
    public void test_boundaries() {
        assertFalse(PalindromeTwo.isPalindrome(-2^20));
        assertFalse(PalindromeTwo.isPalindrome(2^20 - 1));
    }
}