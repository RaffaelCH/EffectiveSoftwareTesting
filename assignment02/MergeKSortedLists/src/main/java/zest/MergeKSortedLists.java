package zest;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * MergeKSortedLists
 *
 * Precondition: The number of nodes in all lists combined is in the range [0, 10^4],
 * and each linked-list is sorted in ascending order.
 *
 * Postcondition: The method returns a single sorted linked list that is the result of merging all k input sorted linked lists.
 *
 * Invariants: All values are smaller or equal than the next and last node and the last node in the list should always have a null next.
 */
public class MergeKSortedLists {

    private void checkPrecondition(ListNode[] lists) {

        // Check if the number of nodes in all lists combined is in the range [0, 10^4]
        int totalNodes = 0;
        for (ListNode node : lists) {
            if (node != null) {
                ListNode current = node;
                while (current != null) {
                    totalNodes++;
                    current = current.next;
                }
            }
        }
        assert totalNodes >= 0 && totalNodes <= Math.pow(10, 4) : "Precondition violated: the number of nodes in all lists combined is in the range [0, 10^4].";

        // Check if each linked-list is sorted in ascending order
        for (ListNode node : lists) {
            ListNode current = node;
            while (current != null && current.next != null) {
                assert current.val <= current.next.val : "Precondition violated: each linked-list is sorted in ascending order.";
                current = current.next;
            }
        }

    }

    private void checkPostcondition(ListNode head) {
        if (head == null) return;

        ListNode current = head;
        while (current != null && current.next != null) {
            assert current.val <= current.next.val : "Postcondition violated: the list is not sorted in ascending order.";
            current = current.next;
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        checkPrecondition(lists);

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {

            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null) {
                queue.add(tail.next);
            }
        }

        assert tail.next == null : "Invariant violated: the last node in the list should have a null next.";
        for (ListNode node : lists) {
            assert node.next == null || node.val <= node.next.val : "Invariant violated: all values are smaller or equal than the next node.";
            assert node.val <= tail.val : "Invariant violated: all values are greater than the last node.";
        }

        checkPostcondition(dummy.next);

        return dummy.next;
    }
}
