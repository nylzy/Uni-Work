class HashMap:
    """A basic key-value HashMap.

    Note: You may not use a python dictionary at any point in this class.

    You should just use Python's built in `hash()` function for hashing keys.
    """

    def __init__(self):
        """Constructs an empty HashMap."""
        # More advanced implemenations exist, but here we will simply use a list
        # for each bucket. Investigate "open addressing" for smarter strategies.
        self.num_buckets = 8
        self.num_elements = 0
        self.buckets = [[] for _ in range(self.num_buckets)]
        

    def __len__(self):
        """Returns the number of elements in the HashMap."""
        return self.num_elements

    def __getitem__(self, key):
        """Returns the value corresponding to the given key in the HashMap.

        Target Complexity: O(1) expected.

        Args:
            key: The key of the desired value.

        Returns:
            The value associated with `key`.

        Raises:
            KeyError: If the key is not in the HashMap.
        """
        item = hash(key)
        locationIndex = item % self.num_buckets
        for i in self.buckets[locationIndex]:
            if i[0] == key:
                return i[1]
        raise KeyError(key)

        

    def __setitem__(self, key, value):
        """Associates `value` with the given `key` in the HashMap.

        Target Complexity: O(1) amortized.

        Any previous associated value is replaced.

        Args:
            key: The key to which to associate `value`.
            value: The value to be associated with `key`.
        """
        item = hash(key)
        locationIndex = item % self.num_buckets
        for index, pair in enumerate(self.buckets[locationIndex]):
            if pair[0] == key:
                self.buckets[locationIndex][index] = (key, value)
                return
        self.buckets[locationIndex].append((key, value))
        self.num_elements += 1

        if self.num_elements / self.num_buckets > 0.75:
            newArray = [[] for _ in range(2 * self.num_buckets)]
            for bucket in self.buckets:
                for pair in bucket:
                    item = hash(pair[0])
                    locationIndex = item % (2 * self.num_buckets)
                    newArray[locationIndex].append(pair)
            self.num_buckets = 2 * self.num_buckets
            self.buckets = newArray

    def __contains__(self, key):
        """Check whether `key` appears in the HashMap.

        Target Complexity: O(1) expected.

        Args:
            key: The key for which to check.

        Returns:
            True if `key` appears in the HashMap, False otherwise.
        """
        item = hash(key)
        locationIndex = item % self.num_buckets
        for i in self.buckets[locationIndex]:
            if i[0] == key:
                return True
        return False

    def remove(self, key):
        """Removes and returns the value associated with `key` in the HashMap.

        Target Complexity: O(1) expected.

        Args:
            key: The key of the entry to remove.

        Returns:
            The value associated with `key`.

        Raises:
            KeyError: If the HashMap does not contain `key`.
        """
        item = hash(key)
        locationIndex = item % self.num_buckets
        for index, pair in enumerate(self.buckets[locationIndex]):
            if pair[0] == key:
                self.num_elements -= 1
                target = self.buckets[locationIndex].pop(index)
                return target[1]
        raise KeyError(key)
            
    def delete(self, key):
        """Deletes `key` from the HashMap, if present.

        Does nothing if key is not already present.

        Target Complexity: O(1) expected.

        Args:
            key: The key to be deleted.

        Returns:
            True if `key` was deleted, False if it was not present.
        """
        if key not in self:
            return False
        self.remove(key)
        return True

    def items(self):
        """Gets a list of all (key, value) pairs from the HashMap.

        No specific order is guaranteed.

        Returns:
            A list of all (key, value) pairs.
        """
        fullList = []
        for bucket in self.buckets:
                for pair in bucket:
                    fullList.append(pair)
        return fullList
