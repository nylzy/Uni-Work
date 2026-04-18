from arraylist import *

def demo_arraylist():
    n = 10   # use small number so output is readable
    arraylist = ArrayList()

    print("Appending values:")
    for i in range(n):
        arraylist.append(i * i)
        print(f"append({i*i}) → length = {len(arraylist)}")

    print("\nArray contents:")
    for i in range(len(arraylist)):
        print(f"arraylist[{i}] = {arraylist[i]}")

    print("\nPopping from back:")
    while len(arraylist) > 0:
        print(f"pop() → {arraylist.pop()}, length = {len(arraylist)}")

    print("\nRebuilding list for pop_front demo:")
    for i in range(n):
        arraylist.append(i)
        print(f"append({i}) → length = {len(arraylist)}")

    print("\nPopping from front:")
    while len(arraylist) > 0:
        print(f"pop_front() → {arraylist.pop_front()}, length = {len(arraylist)}")


if __name__ == "__main__":
    demo_arraylist()
