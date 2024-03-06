package zest;
import org.junit.jupiter.api.*;

class MyAtoiTest {
    int specialCaseOutput = 0;

    @Test
    @DisplayName("Return 0 for null input as per description")
    void testNull() {
        int actual = MyAtoi.myAtoi(null);
        Assertions.assertEquals(specialCaseOutput, actual);
    }

    @Test
    @DisplayName("Return 0 for empty string")
    void testEmpty() {
        int actual = MyAtoi.myAtoi("");
        int actualWithSpace = MyAtoi.myAtoi(" ");

        Assertions.assertEquals(specialCaseOutput, actual);
        Assertions.assertEquals(specialCaseOutput, actualWithSpace);
    }

    @Test
    @DisplayName("Removes leading spaces before returning output for special case")
    void testLeadingSpace() {
        // letters
        int actual = MyAtoi.myAtoi(" a");
        int actualSpaces = MyAtoi.myAtoi("            ab");

        Assertions.assertEquals(specialCaseOutput, actual);
        Assertions.assertEquals(specialCaseOutput, actualSpaces);

        // number
        int expectedNumber = 432;
        int actualNumberWithSpaces = MyAtoi.myAtoi("  " + expectedNumber);
        Assertions.assertEquals(expectedNumber, actualNumberWithSpaces);
    }

    @Test
    @DisplayName("Removes trailing spaces before returning output for special case")
    void testTrailingSpace() {
        // letters
        int actual = MyAtoi.myAtoi("a ");
        int actualSpaces = MyAtoi.myAtoi("a           ");

        Assertions.assertEquals(specialCaseOutput, actual);
        Assertions.assertEquals(specialCaseOutput, actualSpaces);


    }

    @Test
    @DisplayName("Removes both leading and trailing spaces before returning output for special case")
    void testLeadingAndTrailingSpace() {
        // letters
        int actualSpacesBothSides = MyAtoi.myAtoi("          a           ");
        Assertions.assertEquals(specialCaseOutput, actualSpacesBothSides);

        // number
        int expectedNumber = 432903902;
        int actualNumberBothSides = MyAtoi.myAtoi("          432903902          ");
        Assertions.assertEquals(expectedNumber, actualNumberBothSides);
    }

    @Test
    @DisplayName("Returns converted number despite in between space")
    void testInBetweenSpace(){
        int actual = MyAtoi.myAtoi("2344 508 50");
        int actualPlusSign = MyAtoi.myAtoi("+ 234450850");
        int actualMinusSign = MyAtoi.myAtoi("-  234450850");
        int actualWithZeroes = MyAtoi.myAtoi("00 233400");

        Assertions.assertEquals(234450850, actual);
        Assertions.assertEquals(234450850, actualPlusSign);
        Assertions.assertEquals(-234450850, actualMinusSign);
        Assertions.assertEquals(233400, actualWithZeroes);
    }

    @Test
    @DisplayName("Returns converted number for normal number inputs")
    void testNormalNumbers() {
        int actual = MyAtoi.myAtoi("234450850");
        int actualPlusSign = MyAtoi.myAtoi("+234450850");
        int actualMinusSign = MyAtoi.myAtoi("-234450850");
        int actualWithZeroes = MyAtoi.myAtoi("00233400");

        Assertions.assertEquals(234450850, actual);
        Assertions.assertEquals(234450850, actualPlusSign);
        Assertions.assertEquals(-234450850, actualMinusSign);
        Assertions.assertEquals(233400, actualWithZeroes);
    }
}