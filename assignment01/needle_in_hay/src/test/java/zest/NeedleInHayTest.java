package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeedleInHayTest {

    @Test
    public void inputNullTest() {
        assertEquals(-1, NeedleInHay.find("haystack", null));
        assertEquals(-1, NeedleInHay.find(null, "a"));
    }

    @Test
    public void bothInputsEmptyTest() {
        assertEquals(0, NeedleInHay.find("", ""));
    }

    @Test
    public void emptyNeedleTest() {
        assertEquals(0, NeedleInHay.find("haystack", ""));
    }

    @Test
    public void emptyHaystackTest() {
        assertEquals(-1, NeedleInHay.find("", "needle"));
    }

    @Test
    public void needleNotInHaystackTest() {
        assertEquals(-1, NeedleInHay.find("x", "y"));
        assertEquals(-1, NeedleInHay.find("xyzxyz", "a"));
        assertEquals(-1, NeedleInHay.find("x", "xxx"));
        assertEquals(-1, NeedleInHay.find("xyzxyz", "xx"));
    }

    @Test
    public void needleInHaystackTest() {
        assertEquals(0, NeedleInHay.find("x", "x"));
        assertEquals(1, NeedleInHay.find("xyz", "y"));
        assertEquals(0, NeedleInHay.find("xyz", "xyz"));
        assertEquals(5, NeedleInHay.find("xyzabc", "c"));
    }
}
