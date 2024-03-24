package zest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class NeedleInHayTest {

    @Test
    public void inputNullTest() {
        assertEquals(NeedleInHay.find("haystack", null), -1);
        assertEquals(NeedleInHay.find(null, "a"), -1);
    }

    @Test
    public void bothInputsEmptyTest() {
        assertEquals(NeedleInHay.find("", ""), 0);
    }

    @Test
    public void emptyNeedleTest() {
        assertEquals(NeedleInHay.find("haystack", ""), 0);
    }

    @Test
    public void emptyHaystackTest() {
        assertEquals(NeedleInHay.find("", "needle"), -1);
    }

    @Test
    public void needleNotInHaystackTest() {
        assertEquals(NeedleInHay.find("x", "y"), -1);
        assertEquals(NeedleInHay.find("xyzxyz", "a"), -1);
        assertEquals(NeedleInHay.find("x", "xxx"), -1);
        assertEquals(NeedleInHay.find("xyzxyz", "xx"), -1);
    }

    @Test
    public void needleInHaystackTest() {
        assertEquals(NeedleInHay.find("x", "x"), 0);
        assertEquals(NeedleInHay.find("xyz", "y"), 1);
        assertEquals(NeedleInHay.find("xyz", "xyz"), 0);
        assertEquals(NeedleInHay.find("xyzabc", "c"), 5);
    }
}