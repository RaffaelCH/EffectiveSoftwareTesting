package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleTest {

    private final CourseSchedule cs = new CourseSchedule();

    @Test
    void invalidInput(){
        // pre-condition being violated is caught through assertion error
        // numCourses < 0
        int[][] prerequisites = {{1, 0}};
        assertThrows(AssertionError.class, () -> cs.canFinish(-2, prerequisites));

        // prerequisite pair has one value < 0
        prerequisites[0][0] = -1;
        assertThrows(AssertionError.class, () -> cs.canFinish(2, prerequisites));

        // prerequisite pair has two values < 0
        prerequisites[0][1] = -2;
        assertThrows(AssertionError.class, () -> cs.canFinish(2, prerequisites));

        // prerequisite pair has two same elements
        prerequisites[0][0] = -1;
        assertThrows(AssertionError.class, () -> cs.canFinish(2, prerequisites));

        // not pair
        int[][] prerequisites2 = {{1, 0, 2}};
        assertThrows(AssertionError.class, () -> cs.canFinish(3, prerequisites2));
    }

    @Test
    void inputOfSizeMoreThanOne(){
        int[][] prerequisites = {{1, 0}, {2, 1}};
        assertTrue(cs.canFinish(3, prerequisites));
        assertDoesNotThrow(() -> cs.canFinish(3, prerequisites));

        int[][] prerequisites2 = {{1, 0}, {2, 1}, {1, 3}, {4,3}, {4, 6}, {5, 4}, {2, 4}};
        assertTrue(cs.canFinish(7, prerequisites2));
        assertDoesNotThrow(() -> cs.canFinish(7, prerequisites));
    }

    @Test
    void cycleInput(){
        int[][] prerequisites = {{1, 0}, {0, 1}};
        assertFalse(cs.canFinish(2, prerequisites));
        assertDoesNotThrow(() -> cs.canFinish(2, prerequisites));
    }

    @Property
    void inputOfSizeOne(@ForAll List<Integer> prerequisite){
        if(prerequisite.size() == 2 && prerequisite.get(0) >=0 && prerequisite.get(1) >= 0 && prerequisite.get(0) != prerequisite.get(1) && prerequisite.get(1) < 2){
            int[] prerequisiteArray = convertListToArray(prerequisite);
            int[][] inputArray = new int[1][2];
            inputArray[0] = prerequisiteArray;

            assertTrue(cs.canFinish(2, inputArray));
        }
    }

    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers
                .stream()
                .mapToInt(x -> x)
                .toArray();
        return array;
    }
}
