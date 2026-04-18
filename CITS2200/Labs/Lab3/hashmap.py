class HashMap:
    """A basic key-value HashMap.

    Note: You may not use a python dictionary at any point in this class.

    You should just use Python's built in `hash()` function for hashing keys.
    """

    def __init__(self):
        """Constructs an empty HashMap."""
        # More advanced implemenations exist, but here we will simply use a list
        # for each bucket. Investigate "open addressing" for smarter strategies.
        pass

    def __len__(self):
        """Returns the number of elements in the HashMap."""
        pass

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
        pass

    def __setitem__(self, key, value):
        """Associates `value` with the given `key` in the HashMap.

        Target Complexity: O(1) amortized.

        Any previous associated value is replaced.

        Args:
            key: The key to which to associate `value`.
            value: The value to be associated with `key`.
        """
        pass

    def __contains__(self, key):
        """Check whether `key` appears in the HashMap.

        Target Complexity: O(1) expected.

        Args:
            key: The key for which to check.

        Returns:
            True if `key` appears in the HashMap, False otherwise.
        """
        pass

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
        pass

    def delete(self, key):
        """Deletes `key` from the HashMap, if present.

        Does nothing if key is not already present.

        Target Complexity: O(1) expected.

        Args:
            key: The key to be deleted.

        Returns:
            True if `key` was deleted, False if it was not present.
        """
        pass

    def items(self):
        """Gets a list of all (key, value) pairs from the HashMap.

        No specific order is guaranteed.

        Returns:
            A list of all (key, value) pairs.
        """
        pass
