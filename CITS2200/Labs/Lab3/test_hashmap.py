#!/usr/bin/env python3

import unittest
from hashmap import *

class TestHashMap(unittest.TestCase):
    def test_hashmap_example(self):
        n = 10  # smaller number for readable output
        hashmap = HashMap()

        print("\n=== Inserting key-value pairs ===")
        for i in range(n):
            key = f'{i}^2'
            value = i * i
            hashmap[key] = value
            print(f"Inserted: {key} -> {value}")

        print("\n=== Checking values ===")
        for i in range(n):
            key = f'{i}^2'
            print(f"{key} -> {hashmap[key]}")
            self.assertEqual(hashmap[key], i * i)

        print("\n=== HashMap length ===")
        print(len(hashmap))
        self.assertEqual(len(hashmap), n)

    def test_remove_print(self):
        n = 10
        hashmap = HashMap()

        # Insert keys
        for i in range(n):
            hashmap[f'key {i}'] = i

        print("\n=== Removing keys at even indices ===")
        for i in range(0, n, 2):
            removed_value = hashmap.remove(f'key {i}')
            print(f"Removed: key {i} -> {removed_value}")
            self.assertEqual(removed_value, i)

        print("\n=== Remaining keys ===")
        for i in range(n):
            # Using the __contains__ method (so `in` works)
            exists = f'key {i}' in hashmap
            print(f"key {i} in hashmap? {exists}")
            self.assertEqual(exists, i % 2 != 0)

if __name__ == '__main__':
    unittest.main(verbosity=2, buffer=False)

