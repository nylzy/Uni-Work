#!/usr/bin/env python3

import unittest
from sorting import *
from random import Random

SEED = 2200
RUNS = 5
SIZE = 10
RANGE = 50


class TestSorting(unittest.TestCase):
    def _test_sorting(self, sort, name):
        random = Random(SEED)
        for i in range(RUNS):
            xs = [random.randint(0, RANGE) for _ in range(SIZE)]
            expected = sorted(xs)
            received = sort(xs.copy())

            print(f'Run {i}: {name}')
            print(f'  Input:    {xs}')
            print(f'  Expected: {expected}')
            print(f'  Received: {received}\n')

            self.assertEqual(received, expected)

    def test_all_sorts(self):
        sorts = [
            (insertion_sort, 'insertion_sort'),
            (merge_sort, 'merge_sort'),
        ]

        for sort, name in sorts:
            with self.subTest(sort=name):
                self._test_sorting(sort, name)

    def test_merge(self):
        test_cases = [
            ([1, 3, 5], [2, 4, 6]),
            ([1, 2, 3], []),
            ([], [4, 5, 6]),
            ([1, 1, 3], [1, 2, 2]),
            ([], []),
        ]

        for lhs, rhs in test_cases:
            with self.subTest(lhs=lhs, rhs=rhs):
                result = merge(lhs, rhs)
                expected = sorted(lhs + rhs)
                print(f'Merge test:')
                print(f'  LHS:      {lhs}')
                print(f'  RHS:      {rhs}')
                print(f'  Result:   {result}')
                print(f'  Expected: {expected}\n')

                self.assertEqual(result, expected)


if __name__ == '__main__':
    unittest.main()
