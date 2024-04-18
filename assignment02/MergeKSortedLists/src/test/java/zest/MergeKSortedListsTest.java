package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeKSortedListsTest {

    @Nested
    class CodeCoverageTests {

        @Test
        public void testNullList() {
            ListNode[] lists = null;
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(lists);

            assertNull(result);
        }

        @Test
        public void testEmptyList() {
            ListNode[] list = new ListNode[0];
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assertNull(result);
        }

        @Test
        public void testTwoEmptyLists() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode();
            list[1] = new ListNode();
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assert (result.val == 0);
            assertNotNull(result.next);
            assert (result.next.val == 0);
            assertNull(result.next.next);
        }

        @Test
        public void testEmptyNode() {
            ListNode[] list = new ListNode[1];
            list[0] = new ListNode();
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assert (result.val == 0);
            assertNull(result.next);
        }

        @Test
        public void testSingleListSingleNode() {
            ListNode[] list = new ListNode[1];
            list[0] = new ListNode(1);
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assert (result.val == 1);
            assertNull(result.next);
        }

        @Test
        public void testTwoListsSingleNodeEach() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode(1);
            list[1] = new ListNode(2);
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assert (result.val == 1);
            assertNotNull(result.next);
            assert (result.next.val == 2);
            assertNull(result.next.next);
        }

        @Test
        public void testTwoListsMultipleNodesEachAfterEachOther() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode(1, new ListNode(2, new ListNode(3)));
            list[1] = new ListNode(4, new ListNode(5, new ListNode(6)));
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assert (result.val == 1);
            assertNotNull(result.next);
            assert (result.next.val == 2);
            assertNotNull(result.next.next);
            assert (result.next.next.val == 3);
            assertNotNull(result.next.next.next);
            assert (result.next.next.next.val == 4);
            assertNotNull(result.next.next.next.next);
            assert (result.next.next.next.next.val == 5);
            assertNotNull(result.next.next.next.next.next);
            assert (result.next.next.next.next.next.val == 6);
            assertNull(result.next.next.next.next.next.next);
        }

        @Test
        public void testTwoListsMultipleNodesMixed() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode(1, new ListNode(3, new ListNode(5)));
            list[1] = new ListNode(2, new ListNode(4, new ListNode(6)));
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(list);

            assert (result.val == 1);
            assertNotNull(result.next);
            assert (result.next.val == 2);
            assertNotNull(result.next.next);
            assert (result.next.next.val == 3);
            assertNotNull(result.next.next.next);
            assert (result.next.next.next.val == 4);
            assertNotNull(result.next.next.next.next);
            assert (result.next.next.next.next.val == 5);
            assertNotNull(result.next.next.next.next.next);
            assert (result.next.next.next.next.next.val == 6);
            assertNull(result.next.next.next.next.next.next);
        }

    }

    @Nested
    class ContactTests {

        @Test
        public void testPreconditionViolation() {
            ListNode[] list = new ListNode[1];
            list[0] = new ListNode(2, new ListNode(1));
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            assertThrows(AssertionError.class, () -> mergeKSortedLists.mergeKLists(list));
        }

        @Test
        public void testPreconditionUnviolated() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode(1, new ListNode(2));
            list[1] = new ListNode(3, new ListNode(4));
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            assertDoesNotThrow(() -> mergeKSortedLists.mergeKLists(list));
        }

        @Test
        public void testPostconditionUnviolated() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode(1, new ListNode(3));
            list[1] = new ListNode(2, new ListNode(4));
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            assertDoesNotThrow(() -> mergeKSortedLists.mergeKLists(list));
        }

        @Test
        public void testInvariantUnviolated() {
            ListNode[] list = new ListNode[2];
            list[0] = new ListNode(1, new ListNode(2));
            list[1] = new ListNode(3, new ListNode(4));
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            assertDoesNotThrow(() -> mergeKSortedLists.mergeKLists(list));
        }

    }

    @Nested
    class PropertyBasedTests {

        // Use property-based testing techniques to derive tests for the provided Java solutions.
        // Identify properties that should hold true for any inputs and document your rationale.
        // Use a property-based testing framework to automate the testing process.
        // hint: Add jqwik framework to Your pom.xml

        @Property
        @Report(Reporting.GENERATED)
        void testMergeKSortedLists(
                @ForAll("sortedListOfListNode") List<ListNode> lists
        ) {
            // Instantiate your class and call the method to test
            MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
            ListNode result = mergeKSortedLists.mergeKLists(lists.toArray(new ListNode[0]));

            assertNotNull(result);
            // Here you can assert results such as checking if the merged list is sorted
        }

        @Provide
        Arbitrary<List<ListNode>> sortedListOfListNode() {
            return Arbitraries.integers().list().ofMinSize(1).ofMaxSize(10) // Size of each sublist
                    .map(integers -> {
                        integers.sort(Integer::compare);
                        return integers;
                    })
                    .map(this::convertToLinkedList);
        }

        private List<ListNode> convertToLinkedList(List<Integer> values) {
            ListNode dummyHead = new ListNode(0);
            ListNode current = dummyHead;
            for (int value : values) {
                current.next = new ListNode(value);
                current = current.next;
            }
            return Arrays.asList(dummyHead.next); // Returns a single list containing the head of the linked list
        }

    }
 
}