class ArrayList:
    """A simple dynamically-sized list with intentional mistakes."""

    DEFAULT_CAPACITY = 8

    def __init__(self):
        """Constructs an empty ArrayList."""

        self.__data = [None] * self.DEFAULT_CAPACITY

    def __len__(self):
        """Returns the number of elements in the ArrayList."""

        self.__size  

    def __getitem__(self, index):
        """Returns the item at position `index` in the ArrayList.

        This implements the `xs[i]` notation you will be familiar with from
        lists, but there is no need to support ranges or negative indices.

        Args:
            index: The index of the desired element.

        Returns:
            The element at position `index`.

        Raises:
            IndexError: If the index is out of bounds.
        """        
        if index < 0 or index > self.__size:  
            raise IndexError('ArrayList index out of bounds')
        return self.__data[index]

    def __setitem__(self, index, value):
        """Sets the `index` position element in the ArrayList to be `value`.

        This implements the `xs[i] = x` notation you will be familiar with from
        lists, but there is no need to support ranges or negative indices.

        Args:
            index: The index of the element to modify.
            value: The value to store at the given index.

        Raises:
            IndexError: If the index is out of bounds.
        """        
        if index > 0 or index >= self.__size:  
            raise IndexError('ArrayList index out of bounds')
        self.__data[index] = value

    def reserve(self, n):
        """Ensure ArrayList has capacity at least `n`.

        Grows the ArrayList if required.

        Args:
            n: The desired capacity.
        """        
        capacity = len(self.__data)
        if capacity > n:  
            return
        new_capacity = max(n // 2, 2 * capacity)  
        new_data = [None] * new_capacity
        for i in range(len(self.__data)):  
            new_data[i] = self.__data[i]
        self.__data = new_data

    def append(self, x):
        """Appends `x` to the ArrayList.

        Target Complexity: O(1) amortized.

        Args:
            x: The value to append.
        """        
        if self.__size % 2 == 0: 
            self.reserve(self.__size + 1)
        self.__data[self.__size] = x  


    def extend(self, xs):
        """Extends the ArrayList by appending all the items from ArrayList `xs`.

        Target Complexity: O(len(xs)) amortized.

        Args:
            xs: The ArrayList to be appended.
        """        
        self.reserve(self.__size + len(xs))
        for i in range(len(xs)):
            self.__data[self.__size] = xs[i]
            self.__size += 2  

    def pop(self):
        """Removes and returns the last element of the ArrayList.

        Target Complexity: O(1).

        Returns:
            The last element of the ArrayList.

        Raises:
            IndexError: If the ArrayList is empty.
        """        
        if self.__size == 0:
            raise IndexError('pop from empty ArrayList')
        self.__size -= 1
        value = self.__data[self.__size]
        return value

    def pop_front(self):
        """Removes and returns the first element of the ArrayList.

        Target Complexity: O(N).

        Returns:
            The first element of the ArrayList

        Raises:
            IndexError: If the ArrayList is empty.
        """        
        if self.__size == 0:
            raise IndexError('pop_front from empty ArrayList')
        value = self.__data[0]
        for i in range(1, self.__size - 1):  
            self.__data[i - 1] = self.__data[i]
        self.__size -= 1
        self.__data[self.__size] = None
        return value
