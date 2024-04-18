package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class MergeKSortedListsTest {

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

        assert(result.val == 0 );
        assertNotNull(result.next);
        assert(result.next.val == 0);
        assertNull(result.next.next);
    }

    @Test
    public void testEmptyNode() {
        ListNode[] list = new ListNode[1];
        list[0] = new ListNode();
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode result = mergeKSortedLists.mergeKLists(list);

        assert(result.val == 0);
        assertNull(result.next);
    }

    @Test
    public void testSingleListSingleNode() {
        ListNode[] list = new ListNode[1];
        list[0] = new ListNode(1);
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode result = mergeKSortedLists.mergeKLists(list);

        assert(result.val == 1);
        assertNull(result.next);
    }

    @Test
    public void testTwoListsSingleNodeEach() {
        ListNode[] list = new ListNode[2];
        list[0] = new ListNode(1);
        list[1] = new ListNode(2);
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode result = mergeKSortedLists.mergeKLists(list);

        assert(result.val == 1);
        assertNotNull(result.next);
        assert(result.next.val == 2);
        assertNull(result.next.next);
    }

    @Test
    public void testTwoListsMultipleNodesEachAfterEachOther() {
        ListNode[] list = new ListNode[2];
        list[0] = new ListNode(1, new ListNode(2, new ListNode(3)));
        list[1] = new ListNode(4, new ListNode(5, new ListNode(6)));
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode result = mergeKSortedLists.mergeKLists(list);

        assert(result.val == 1);
        assertNotNull(result.next);
        assert(result.next.val == 2);
        assertNotNull(result.next.next);
        assert(result.next.next.val == 3);
        assertNotNull(result.next.next.next);
        assert(result.next.next.next.val == 4);
        assertNotNull(result.next.next.next.next);
        assert(result.next.next.next.next.val == 5);
        assertNotNull(result.next.next.next.next.next);
        assert(result.next.next.next.next.next.val == 6);
        assertNull(result.next.next.next.next.next.next);
    }

    @Test
    public void testTwoListsMultipleNodesMixed() {
        ListNode[] list = new ListNode[2];
        list[0] = new ListNode(1, new ListNode(3, new ListNode(5)));
        list[1] = new ListNode(2, new ListNode(4, new ListNode(6)));
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode result = mergeKSortedLists.mergeKLists(list);

        assert(result.val == 1);
        assertNotNull(result.next);
        assert(result.next.val == 2);
        assertNotNull(result.next.next);
        assert(result.next.next.val == 3);
        assertNotNull(result.next.next.next);
        assert(result.next.next.next.val == 4);
        assertNotNull(result.next.next.next.next);
        assert(result.next.next.next.next.val == 5);
        assertNotNull(result.next.next.next.next.next);
        assert(result.next.next.next.next.next.val == 6);
        assertNull(result.next.next.next.next.next.next);
    }

 
}