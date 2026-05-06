# Name: THOMAS GRAHAM NYLUND
# Student Number: 22708177

class Genealogy:

    class Tree:
        # Prompt: "Generate Python code for a tree node class that stores a name,
        # a parent reference, and a list of children. The node should support O(1)
        # child appending. Do not use any imports."
        def __init__(self, name, parent=None):
            # name: the identifier for this Kiktil individual
            # parent: reference to the parent Tree node (None for the originator)
            # children: ordered list of child nodes, oldest first
            self.name = name
            self.parent = parent
            self.children = []

    class Queue:
        # Prompt: "Generate Python code for a circular array-based FIFO queue
        # (no imports). Include push(item), pop(), push_many(iterable), __len__,
        # and __bool__. The array should double in capacity when full and never
        # use list.insert(). Explain why this gives O(1) amortised push and pop."
        DEFAULT_CAPACITY = 8

        def __init__(self):
            # head: index of the front element
            # size: number of elements currently in the queue
            # data: fixed-size list used as a circular buffer
            self._data = [None] * self.DEFAULT_CAPACITY
            self._head = 0
            self._size = 0

        def __len__(self):
            return self._size

        def __bool__(self):
            return self._size > 0

        def _resize(self):
            # Double capacity and linearise the circular layout
            old_cap = len(self._data)
            new_data = [None] * (old_cap * 2)
            for i in range(self._size):
                new_data[i] = self._data[(self._head + i) % old_cap]
            self._data = new_data
            self._head = 0

        def push(self, item):
            # O(1) amortised: resize doubles capacity so resizing cost is spread
            if self._size == len(self._data):
                self._resize()
            tail = (self._head + self._size) % len(self._data)
            self._data[tail] = item
            self._size += 1

        def pop(self):
            # O(1): just advance head pointer
            if self._size == 0:
                raise IndexError("pop from empty queue")
            item = self._data[self._head]
            self._data[self._head] = None  # allow GC
            self._head = (self._head + 1) % len(self._data)
            self._size -= 1
            return item

        def push_many(self, iterable):
            for item in iterable:
                self.push(item)

    # ------------------------------------------------------------------

    def __init__(self, originator_name):
        # Prompt: "Generate Python code to initialise a Genealogy from a single
        # originator name. Create a Tree node for the originator and store it in
        # a dictionary keyed by name so every future lookup is O(1)."
        root = Genealogy.Tree(originator_name)
        # _nodes maps name -> Tree node for O(1) access in add_child and traversals
        self._nodes = {originator_name: root}
        self._root = root

    def add_child(self, parent_name, child_name):
        # Prompt: "Generate Python code to add a new child node to a parent node
        # in O(1) time. Look up the parent in the dictionary, create a new Tree
        # node with that parent reference, append it to the parent's children list
        # (append is O(1)), and register it in the dictionary."
        parent_node = self._nodes[parent_name]
        child_node = Genealogy.Tree(child_name, parent=parent_node)
        parent_node.children.append(child_node)   # O(1) — no insert()
        self._nodes[child_name] = child_node

    def get_primogeniture_order(self):
        # Prompt: "Generate Python code to return primogeniture order as a list of
        # names using an iterative depth-first traversal with a stack (no recursion,
        # no imports). Start with the root on the stack. Each iteration: pop a node,
        # append its name to the result, then push its children onto the stack in
        # REVERSE order (youngest first) so the eldest is processed next. This
        # ensures parent before eldest child, and a child's entire subtree before
        # the next sibling."
        result = []
        # Stack holds Tree nodes; we use a plain list with append/pop (both O(1))
        stack = [self._root]
        while stack:
            node = stack.pop()
            result.append(node.name)
            # Push children reversed so eldest ends up on top of the stack
            for child in reversed(node.children):
                stack.append(child)
        return result

    def get_seniority_order(self):
        # Prompt: "Generate Python code to return seniority order as a list of names
        # using a breadth-first traversal with the custom Queue class (no imports).
        # Start by pushing the root. Each iteration: pop a node, append its name,
        # then push its children in birth order (oldest first). BFS naturally visits
        # all nodes at depth d before any node at depth d+1, and children are
        # enqueued oldest-first so the within-level ordering is correct."
        result = []
        q = Genealogy.Queue()
        q.push(self._root)
        while q:
            node = q.pop()
            result.append(node.name)
            q.push_many(node.children)   # children already stored oldest-first
        return result

    def get_ancestors(self, name):
        # Prompt: "Generate Python code to return an ordered list of ancestors of
        # the node with the given name, starting from the node's parent and ending
        # at the root (the originator). Follow parent references upward; this is
        # O(d) where d is the depth of the node, which is optimal."
        ancestors = []
        node = self._nodes[name].parent
        while node is not None:
            ancestors.append(node.name)
            node = node.parent
        return ancestors

    def get_cousin_dist(self, lhs_name, rhs_name):
        # Prompt: "Generate Python code to compute the cousin distance between two
        # individuals as a tuple (degree, removal).
        #
        # Algorithm:
        # 1. Build the ancestor chain for lhs as an ordered list from lhs up to root.
        # 2. Store each ancestor's name mapped to its depth-from-lhs in a dictionary.
        # 3. Walk up from rhs, tracking depth-from-rhs. The first ancestor that also
        #    appears in lhs's ancestor dict is the Lowest Common Ancestor (LCA).
        # 4. Let d_l = depth of lhs from LCA, d_r = depth of rhs from LCA.
        #    degree  = min(d_l, d_r) - 1
        #    removal = abs(d_l - d_r)
        # 5. Return (degree, removal).
        #
        # This handles direct ancestor/descendant relationships (degree becomes -1)
        # and siblings (degree=0, removal=0) correctly.
        # Complexity: O(d_l + d_r) where d_l, d_r are depths of lhs and rhs."

        # Step 1 & 2: map each ancestor of lhs to its depth from lhs
        lhs_ancestors = {}  # name -> depth from lhs (lhs itself at depth 0)
        node = self._nodes[lhs_name]
        depth = 0
        while node is not None:
            lhs_ancestors[node.name] = depth
            depth += 1
            node = node.parent

        # Step 3: walk up from rhs to find LCA
        node = self._nodes[rhs_name]
        d_r = 0
        while node is not None:
            if node.name in lhs_ancestors:
                d_l = lhs_ancestors[node.name]
                # Step 4: compute degree and removal
                degree = min(d_l, d_r) - 1
                removal = abs(d_l - d_r)
                return (degree, removal)
            d_r += 1
            node = node.parent

        # Should never reach here in a valid single-rooted genealogy
        return None