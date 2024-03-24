package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.HashSet;

public class GenerateParenthesesTest {

    @Test
    public void testNegativeInput() {
        assertTrue(GenerateParentheses.generateParentheses(-1).isEmpty(),
                "Empty list returns if the n is negative");
    }

    @Test
    public void testNoneParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(0);
        assertTrue(result.isEmpty(), "Empty list returns if the n is 0");
    }

    @Test
    public void testOneParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(1);
        assertEquals(1, result.size(), "List size should be 1 for n=1");
        assertTrue(result.contains("()"), "List should contain \"()\"");
    }

    @Test
    public void testTwoParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(2);
        HashSet<String> expected = new HashSet<>(List.of("(())", "()()"));
        assertEquals(2, result.size(), "List size should be 2 for n=2");
        assertTrue(result.containsAll(expected), "List should contain \"(())\" and \"()()\"");
    }

    @Test
    public void testThreeParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(3);
        HashSet<String> expected = new HashSet<>(List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertEquals(5, result.size(), "List size should be 5 for n=3");
        assertTrue(result.containsAll(expected), "List should match expected combinations for n=3");
    }

    @Test
    public void testSizeNParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(4);
        assertEquals(14, result.size(), "List size should be 14 for n=4");
    }
}
