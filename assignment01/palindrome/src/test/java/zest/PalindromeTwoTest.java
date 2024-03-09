package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeTwoTest {
    @Test
    void single_digit_is_palindrome() {
        assertTrue(PalindromeTwo.isPalindrome(0));
        assertTrue(PalindromeTwo.isPalindrome(1));
    }

    @Test
    void multiple_digits_palindrome() {
        assertTrue(PalindromeTwo.isPalindrome(22));
        assertTrue(PalindromeTwo.isPalindrome(999));
        assertTrue(PalindromeTwo.isPalindrome(1_000_001));
    }

    @Test
    void multiple_digits_not_palindrome() {
        assertFalse(PalindromeTwo.isPalindrome(10));
        assertFalse(PalindromeTwo.isPalindrome(999990));
    }

    @Test
    void negative_number_not_palindrome() {
        assertFalse(PalindromeTwo.isPalindrome(-1));
        assertFalse(PalindromeTwo.isPalindrome(-10));
        assertFalse(PalindromeTwo.isPalindrome(-1_000_001));
    }

    @Test
    void test_boundaries() {
        assertFalse(PalindromeTwo.isPalindrome(-2^20));
        assertFalse(PalindromeTwo.isPalindrome(2^20 - 1));
    }
}