# CITS2200 Lab 2: Genealogy

Name: THOMAS GRAHAM NYLUND

Student Number: 22708177


## Question 1 (1 mark)
Write a simple description of how you are going to represent the problem as a data structure.
Your description should justify how the representation is going to help you solve the problem within the target complexities.

- Each individual is stored as a `Tree` node with their name, a reference to their parent node, and an ordered list of children (oldest first). All nodes are also stored in a dictionary keyed by name, so any node can be retrieved in O(1) time.
- The parent reference lets `get_ancestors()` and `get_cousin_dist()` walk up to the root in O(d) time (where d is depth), without needing to search the whole tree. The children list lets DFS and BFS traversals visit every node in O(n) time. Appending to the children list is O(1), so `add_child` is O(1) overall. These two links give efficient access in both directions (downward for traversals and upward for ancestor queries), which is exactly what the three main operations need.


## Question 2 (1 mark)
Write a simple description of the algorithm you have designed for `get_cousin_dist()`.
Your description should justify the correctness of your algorithm, and make an argument as to its time complexity.

The algorithm finds the Lowest Common Ancestor (LCA) of the two individuals, then uses the depth of each individual from the LCA to compute degree and removal.

**Steps:**
1. Walk from `lhs` up to the root, recording each ancestor's name and its depth from `lhs` in a dictionary.
2. Walk from `rhs` upward. The first node whose name appears in the dictionary is the LCA. Let `d_l` be lhs's depth from the LCA and `d_r` be rhs's depth from the LCA.
3. Return `(min(d_l, d_r) - 1, abs(d_l - d_r))`.

**Correctness:** Two individuals are nth cousins if their nearest common ancestor is n+1 generations above both of them, which gives `min(d_l, d_r) - 1`. They are `k` times removed if one is `k` generations further from the LCA than the other, which gives `abs(d_l - d_r)`. When one is a direct ancestor of the other, `min(d_l, d_r) = 0`, giving degree `-1`, which correctly identifies an ancestor/descendant relationship. Siblings share a parent at depth 1, giving degree 0, removal 0, which matches the convention.

**Complexity:** Step 1 is O(d_l) and step 2 is O(d_r), where d_l and d_r are the depths of lhs and rhs respectively. The overall complexity is O(d_l + d_r), which is O(n) in the worst case (a degenerate chain), but O(log n) for balanced trees.


## Question 3 (5 marks)
Implement your design by filling out the method stubs in the `Genealogy` class found in `genealogy.py`.
You are **not** allowed to import any modules.
Your implementation must pass the tests given in `test_genealogy.py`, which can be invoked by running `python -m unittest`.

See `genealogy.py`.


## Question 4 (1 mark)
Give an argument for the correctness and complexity of your `get_primogeniture_order()` function.

**Correctness:** The function uses an iterative DFS with an explicit stack. The root is pushed first. On each iteration, the current node is visited and its children are pushed in reverse order (youngest first), so the eldest child sits on top of the stack. This means after visiting a node, the eldest child and its entire subtree are visited before any younger sibling, which is exactly the primogeniture rule (parent, then eldest child's line, then next sibling, and so on).

**Complexity:** Every node is pushed onto and popped from the stack exactly once, and each push/pop is O(1). The reversal of children at each node visits each child once across the whole traversal. Total time is O(n), where n is the number of nodes. Space is O(n) for the stack in the worst case (a path-shaped tree).


## Question 5 (1 mark)
Give an argument for the correctness and complexity of your `get_seniority_order()` function.

**Correctness:** The function uses BFS with a FIFO queue. Starting from the root, each node is dequeued, visited, and its children are enqueued in birth order (oldest first). BFS visits all nodes at depth d before any node at depth d+1, so all members of one generation are listed before the next. Within a generation, nodes appear in the order their parents were enqueued, and within the same parent, in birth order. This is exactly the seniority rule (proximity to originator, then oldest-ancestor priority, then birth order within siblings).

**Complexity:** Every node is enqueued and dequeued exactly once, each in O(1) amortised time (the circular buffer doubles capacity as needed). Enqueuing children is O(1) per child. Total time is O(n). Space is O(w) for the queue, where w is the maximum width of the tree.


## Question 6 (1 mark)
Give a brief explanation of the function and purpose of any data structures you implemented.

**`Tree` node:** Stores a single individual's name, a reference to their parent node, and an ordered list of children. The parent link enables upward traversal for ancestor queries in O(depth) time. The children list enables downward traversal for DFS/BFS. Children are stored oldest-first by insertion order so traversals naturally respect birth order.

**`Queue`:** A circular array-based FIFO queue used for BFS in `get_seniority_order()`. A circular buffer was chosen over a plain list because `list.pop(0)` is O(n), while the circular buffer achieves O(1) amortised push and O(1) pop by maintaining head and tail indices and doubling capacity when full. This keeps the overall BFS at O(n).

**`_nodes` dictionary (in `Genealogy`):** Maps every individual's name to their `Tree` node, giving O(1) lookup by name. This is used by `add_child`, `get_cousin_dist`, and `get_ancestors` to access any node directly without traversing the tree.