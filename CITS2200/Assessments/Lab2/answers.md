Name: THOMAS GRAHAM NYLUND

Student Number: 22708177





Question 1 (1 mark)

Explain the relationship between this problem and more abstract computer science topics covered in class.

Part A – ChatGPT Answer:
(Paste the ChatGPT-generated response here)

Part B – Your Answer:
This problem is an application of ordered list operations, a core abstract topic in computer science. The leaderboard must remain sorted at all times to support efficient rank lookups, which maps directly to the problem of maintaining and searching sorted sequences. Specifically, it requires sorting an initial list (merge sort or insertion sort), inserting new elements into a sorted list (insertion sort), and finding positions or counts within that list (binary search). Each of these we have covered as 'abstract topics' in class, but require practical applications here.





Question 2 (1 mark)

What data do you need to store in the Leaderboard class?
What algorithm do you intend to use for each method?

Part A – ChatGPT Answer:
(Paste the ChatGPT-generated response here)

Part B – Your Answer:
Data:
- For the __init__ method, it will require a list of tuples, with the first element of the tuple being time and the second element being name. This allows for the tuples to be sorted by time (lists sort tuples by their first index)

Methods:
- __init__: **merge sort** to sort the input list into a sorted list.
- get_runs: **no algorithm** required, just returning the list in O(n)
- submit_run: **binary search** to find the correct position, then insert at the correct position
- get_rank_time: **binary search** through the sorted list to check what time would be required to achieve a given rank
- get_possible_rank: **binary search** to search through the sorted list to see what a rank a hypothetical time would receive
- count_time: **binary search** to find the right and left boundaries (references/bounds) that match the input time. Subtracting the left from the right boundary to find the total amount of scores that match the input time.





Question 3 (5 marks)

Implement your design by filling out the method stubs in speedrunning.py.
Your implementation must pass the tests in test\_speedrunning.py.

This question is assessed only on your code.





Question 4 (1 mark)

Give an argument for the correctness and complexity of your **init**() function.

Part A – ChatGPT Answer:
(Paste the ChatGPT-generated response here)

Part B – Your Answer:
- My implementation of __init__ utilises the merge sort algorithm to break the list of tuples down recursively into sub lists of either 1 or 0 elements and then remerges them in a sorted manner. Specifically this is implemented through the use of two helper functions that I created; _merge and _merge_sort. I did this to better demonstrate which components are taking which function.
- The correctness of merge sort relies on the fact that a list of 0 or 1 elements is trivially sorted. When merging two sorted halves, the smallest remaining element must always be at the front of one of the two halves — so by repeatedly comparing the two front elements and taking the smaller one, we are guaranteed to produce a sorted result. This argument applies at every level of recursion, so the final result is always fully sorted.
- For complexity, each split halves the list, meaning there are O(log n) levels of recursion. At each level, every element is touched exactly once during the merge step, giving O(n) work per level. Combined this gives a total time complexity of O(n log n).





Question 5 (1 mark)

Give an argument for the correctness and complexity of your submit\_run() function.

Part A – ChatGPT Answer:
(Paste the ChatGPT-generated response here)

Part B – Your Answer:
- My submit_run function uses binary search to find the correct insertion index, then inserts the new run at that position using list.insert(). The binary search works by maintaining a range [lo, hi] that narrows down to the correct position. At each step, the midpoint is compared to the new run — if the midpoint is smaller or equal, lo moves right past it; otherwise hi moves left to it. When the loop ends, lo and hi have converged to the same point where everything to the left is smaller and everything to the right is larger, guaranteeing the correct insertion point and maintaining sorted order.
- For complexity, binary search halves the search range each step, giving O(log n) to find the position. However list.insert() must shift all elements to the right of the insertion point, which is O(n) in the worst case. So the overall complexity of submit_run is O(n).





Question 6 (1 mark)

Give an argument for the correctness and complexity of your count\_time() function.

Part A – ChatGPT Answer:
(Paste the ChatGPT-generated response here)

Part B – Your Answer:
- My count_time function uses two binary searches to find the left and right boundaries of all runs with the given time, then subtracts them to get the count. The left boundary finds the first index where the time is equal to the target, and the right boundary finds the first index where the time is greater than the target. Everything between these two boundaries must have exactly the target time, so right - left gives the correct count. This is correct because the list is always sorted, meaning all runs with the same time are guaranteed to be grouped together as a contiguous block, making the boundary approach valid.
- For complexity, each binary search halves the search range at every step, giving O(log n) per search. Since we run exactly two binary searches, the total complexity is O(2 log n) which simplifies to O(log n).

