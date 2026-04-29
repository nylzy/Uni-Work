# Name: YOUR NAME
# Student Number: 23XXXXXX

class Genealogy:

    class Tree:
        # Prompt: "Generate Python code for a tree node class that stores a name, a parent reference, and a list of children. Include an __init__ method with explanations."

    class Queue:
        # Prompt: "Generate Python code for an array-based FIFO queue with push,pop, push_many, __len__, and __bool__ methods. Include explanations for why it is used for BFS."

        DEFAULT_CAPACITY = 8


    def __init__(self, originator_name):
    # Prompt: "Generate Python code to initialize a genealogy starting from a single originator. 
    # Explain how a dictionary can help track nodes by name."      

    def add_child(self, parent_name, child_name):
    # Prompt: "Generate Python code for adding a child to a parent in the genealogy tree in O(1) time."
      

    def get_primogeniture_order(self):
    # Prompt: "Generate Python code to return the primogeniture order (parent first, eldest child to youngest). Explain using depth-first traversal with a stack."
       

    def get_seniority_order(self):
    # Prompt: "Generate Python code to return the seniority order (level by level, oldest siblings first). Explain using breadth-first traversal with a queue."
       

    def get_cousin_dist(self, lhs_name, rhs_name):
    # Prompt: "Generate Python code to compute cousin degree and removal using shared ancestors. Include explanation."
        

    def get_ancestors(self, name):
    # Prompt: "Generate Python code to return all ancestors of a node back to the root. Explain why following parent references is efficient."
       