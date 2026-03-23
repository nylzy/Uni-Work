#!/usr/bin/env python3

from searching import *
from random import Random

SEED = 2200
RUNS = 10  # Reduce for easier output
SIZE = 20  # Reduce for easier output
RANGE = 50

# -----------------------------
# Test cube_root
# -----------------------------
print("=== Testing cube_root ===")
xs = [0, 1, 2, 3, 4, 4.4, -2, 0.1, -0.1]
for x in xs:
    r = cube_root(x)
    if r is None:
        print(f"cube_root({x}) = None (negative input)")
    else:
        print(f"cube_root({x}) = {r:.8f} (cube ≈ {r*r*r:.8f})")

# -----------------------------
# Test lower_bound
# -----------------------------

print("\n=== Testing lower_bound with x in the list ===")
random = Random(SEED)

for i in range(RUNS):
    size = random.randint(1, SIZE)  # ensure list is not empty
    xs = [random.randint(0, RANGE) for _ in range(size)]
    xs.sort()

    # Pick x randomly from the list
    x = xs[random.randint(0, len(xs) - 1)]

    expected = sum(y < x for y in xs)
    received = lower_bound(xs.copy(), x)

    print(f"Test {i+1}: xs = {xs}")
    print(f"x = {x}, lower_bound = {received}, expected = {expected}")
    print("---")


# -----------------------------
# Test upper_bound
# -----------------------------
print("\n=== Testing upper_bound with x in the list ===")
random = Random(SEED)

for i in range(RUNS):
    size = random.randint(1, SIZE)  # ensure list is not empty
    xs = [random.randint(0, RANGE) for _ in range(size)]
    xs.sort()

    # Pick x randomly from the list
    x = xs[random.randint(0, len(xs) - 1)]

    expected = sum(y <= x for y in xs)
    received = upper_bound(xs.copy(), x)

    print(f"Test {i+1}: xs = {xs}")
    print(f"x = {x}, upper_bound = {received}, expected = {expected}")
    print("---")

