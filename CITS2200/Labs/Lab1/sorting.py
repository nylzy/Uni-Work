'''
def insertion_sort(xs):
    """Sorts the given list using insertion sort.

    Args:
        xs: The list to be sorted.

    Returns:
        The list in ascending order.
    """
    result = list(xs)
    for i in range(1, len(result)):
        key = result[i]
        j = i - 1
        while j >= 0 and result[j] > key:
            result[j + 1] = result[j]
            j -= 1
        result[j + 1] = key
    return result
'''
    

def merge(lhs, rhs):
    """Merges a pair of sorted lists.

    Args:
        lhs: A sorted list to be merged with rhs.
        rhs: A sorted list to be merged with lhs.

    Returns:
        A sorted list containing all the elements of lhs and rhs.
    """
    result = []
    i = j = 0
    while i < len(lhs) and j < len(rhs):
        if lhs[i] <= rhs[j]:
            result.append(lhs[i])
            i += 1
        else:
            result.append(rhs[j])
            j += 1
    result.extend(lhs[i:])
    result.extend(rhs[j:])
    return result



def merge_sort(xs):
    """Sorts the given list using merge sort.

    Args:
        xs: The list to be sorted.

    Returns:
        The list in ascending order.
    """
    if len(xs) <= 1:
        return list(xs)
    mid = len(xs) // 2
    left = merge_sort(xs[:mid])
    right = merge_sort(xs[mid:])
    return merge(left, right)



def insertion_sort(xs):
    result = list(xs)
    for i in range(1, len(xs)):
        key = result[i]
        j = i - 1
        while j >= 0 and result[j] > key:
            result[j+1] = result[j]
            j -= 1
        result[j+1] = key
    return result