package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NeedleInHayTest {

    @Test
    void inputNullTest() {
        assertEquals(NeedleInHay.find("haystack", null), -1);
        assertEquals(NeedleInHay.find(null, "a"), -1);
    }

    @Test
    void bothInputsEmptyTest() {
        assertEquals(NeedleInHay.find("", ""), 0);
    }

    @Test
    void emptyNeedleTest() {
        assertEquals(NeedleInHay.find("haystack", ""), 0);
    }

    @Test
    void emptyHaystackTest() {
        assertEquals(NeedleInHay.find("", "needle"), -1);
    }

    @Test
    void needleNotInHaystackTest() {
        assertEquals(NeedleInHay.find("x", "y"), -1);
        assertEquals(NeedleInHay.find("xyzxyz", "a"), -1);
        assertEquals(NeedleInHay.find("x", "xxx"), -1);
        assertEquals(NeedleInHay.find("xyzxyz", "xx"), -1);
    }

    @Test
    void needleInHaystackTest() {
        assertEquals(NeedleInHay.find("x", "x"), 0);
        assertEquals(NeedleInHay.find("xyz", "y"), 1);
        assertEquals(NeedleInHay.find("xyz", "xyz"), 0);
        assertEquals(NeedleInHay.find("xyzabc", "c"), 5);
    }
}