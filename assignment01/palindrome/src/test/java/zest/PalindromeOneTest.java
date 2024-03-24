package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        assertTrue(PalindromeOne.isPalindrome(1000001));
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
        assertFalse(PalindromeOne.isPalindrome(-1000001));
    }

    @Test
    public void test_boundaries() {
        assertFalse(PalindromeOne.isPalindrome((int) -Math.pow(2, 20)));
        assertFalse(PalindromeOne.isPalindrome((int) (Math.pow(2, 20) - 1)));
    }
}
