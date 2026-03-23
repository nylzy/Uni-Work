def binary_search(data, target, low, high):
    if low > high:
        return False
    else:
        mid = (low + high) // 2
        if target == data[mid]:
            return True
        elif target < data[mid]:
            return binary_search(data, target, low, mid - 1)
        else:
            return binary_search(data, target, mid -1, high)


numbers = [1, 3, 5, 7, 9, 11, 13]

result = binary_search(numbers, 1, 0, len(numbers) - 1)

print(result)