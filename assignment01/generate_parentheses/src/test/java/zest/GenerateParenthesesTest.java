package zest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.HashSet;
import org.junit.Test;

public class GenerateParenthesesTest {

    @Test
    public void testNegativeInput() {
        assertTrue("Empty list returns if the n is negative", 
            GenerateParentheses.generateParentheses(-1).isEmpty());
    }

    @Test
    public void testNoneParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(0);
        assertTrue("Empty list returns if the n is 0", result.isEmpty());
    }

    @Test
    public void testOneParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(1);
        assertEquals("List size should be 1 for n=1", 1, result.size());
        assertTrue("List should contain \"()\"", result.contains("()"));
    }

    @Test
    public void testTwoParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(2);
        HashSet<String> expected = new HashSet<>(List.of("(())", "()()"));
        assertEquals("List size should be 2 for n=2", 2, result.size());
        assertTrue("List should contain \"(())\" and \"()()\"", result.containsAll(expected));
    }

    @Test
    public void testThreeParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(3);
        HashSet<String> expected = new HashSet<>(List.of("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertEquals("List size should be 5 for n=3", 5, result.size());
        assertTrue("List should match expected combinations for n=3", result.containsAll(expected));
    }

    @Test
    public void testSizeNParentheses() {
        List<String> result = GenerateParentheses.generateParentheses(4);
        assertEquals("List size should be 14 for n=4", 14, result.size());
    }
}


