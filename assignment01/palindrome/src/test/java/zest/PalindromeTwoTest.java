package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        assertTrue(PalindromeTwo.isPalindrome(1000001));
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
        assertFalse(PalindromeTwo.isPalindrome(-1000001));
    }

    @Test
    public void test_boundaries() {
        assertFalse(PalindromeTwo.isPalindrome((int) -Math.pow(2, 20)));
        assertFalse(PalindromeTwo.isPalindrome((int) (Math.pow(2, 20) - 1)));
    }
}
