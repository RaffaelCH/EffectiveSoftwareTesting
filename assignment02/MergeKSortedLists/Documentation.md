# MergeKSortedLists
## Task 1: Code Coverage
In this task, we applied structural testing, trying to reach 100% line coverage, which we achieved successfully.
All lines of code (LOC) are covered by the tests. 
We could have used fewer tests to reach 100% line coverage, but we wanted to test all possible cases, having a more robust test suite, not only trying to achieve a certain percentage.
We used the JaCoCo plugin to analyze and generate coverage reports.
The screenshots can be found in the ['Assets' folder](./Assets) as described in the assignment's instructions.
## Task 2: Designing Contracts
### Pre-Conditions
We found two pre-conditions from the requirements (task description).
In the book was described that the code for the invariant can be extracted into a separate method, which we did for the pre-conditions in order to keep the code clean and readable.

**Pre-Condition 1:** The number of nodes in all lists combined is in the range [0, 10^4].
We checked that with the following piece of code, by looping through all lists and counting the nodes:
```java
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
```

**Pre-Condition 2:** Each linked-list is sorted in ascending order.
We check that by, again, looping through all lists and checking if the current node is smaller or equal to the next node:
```java
for (ListNode node : lists) {
    ListNode current = node;
    while (current != null && current.next != null) {
        assert current.val <= current.next.val : "Precondition violated: each linked-list is sorted in ascending order.";
        current = current.next;
    }
}
```

### Post-Conditions
Again, we extracted the checking of the post-conditions into a separate method, as described in the pre-conditions section before.
The in the requirements / task description mentioned post-condition is that the method returns a single sorted linked list that is the result of merging all k input sorted linked lists.
We checked that by looping through the result list and checking if the current node is smaller or equal to the next node:
```java
if (head == null) return;

ListNode current = head;
while (current != null && current.next != null) {
    assert current.val <= current.next.val : "Postcondition violated: the list is not sorted in ascending order.";
    current = current.next;
}
```


### Invariants
It was difficult to find invariants, at clearer invariants can be found in programs which modify some values, and pass them along to other parts of the code (such as in an pipes-and-filters architecture).
Nevertheless, we found appropriate invariants for the `MergeKSortedLists` algorithm.
We tested them at the end of the algorithm. 
If wanted, you could also check the invariants multiple times in the middle of the code, for example in the while loop, to ensure that the invariant <u>always</u> hold. 

**Invariant 1:** All values in the list are smaller or equal than the next and smaller or equal than the last node (sorted ascending).
We checked that with the following code: `assert node.next == null || node.val <= node.next.val` and `assert node.val <= tail.val`
The null check in the first piece of code exists because the last node can have a null next.


**Invariant 2:** The last node in the list should always have a null next.
Code: `assert tail.next == null;`
