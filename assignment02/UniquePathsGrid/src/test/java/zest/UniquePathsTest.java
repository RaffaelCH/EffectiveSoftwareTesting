package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsTest {

    UniquePaths uniquePaths = new UniquePaths();

    @Test
    public void smallestGridTest() {
        assertEquals(1, uniquePaths.uniquePaths(1, 1));
    }

    @Test
    public void oneDimensionalGridTest() {
        assertEquals(1, uniquePaths.uniquePaths(1, 100));
        assertEquals(1, uniquePaths.uniquePaths(100, 1));
    }

    @Test
    public void normalGridTest() {
        assertEquals(28, uniquePaths.uniquePaths(3, 7));
        assertEquals(6, uniquePaths.uniquePaths(3, 3));
        assertEquals(100, uniquePaths.uniquePaths(2, 100));
    }

    @Test
    public void preconditionTest() {
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(0, 1));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(1, 0));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(1, -100));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(-1, -1));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(1, 101));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(101, 1));
        assertThrows(IllegalArgumentException.class, () -> uniquePaths.uniquePaths(10000, 101));
    }

    @Test
    public void postconditionTest() {
        assertFalse(uniquePaths.uniquePaths(80, 80) < 0);
        assertFalse(uniquePaths.uniquePaths(90, 100) < 0);
        assertFalse(uniquePaths.uniquePaths(100, 100) < 0);
    }
}