# Name: THOMAS GRAHAM NYLUND
# Student Number: 22708177

class Leaderboard:
    """A leaderboard of speedrunning record times.

    Each entry has a time in seconds and a runner name.
    Runners may submit multiple runs.
    The leaderboard is ranked fastest run first.
    Ties receive the same rank as each other, so for example if runners submit
    the times 10, 20, 20, and 30, they will have the ranks 1, 2, 2, and 4.
    """

    def __init__(self, runs=[]):
        """Constructs a leaderboard with the given runs.

        The given list of runs is not required to be in order.

        Args:
            runs: Initial leaderboard entries as list of (time, name) pairs.
        """
        self.runs = self._merge_sort(runs)

    def _merge(self, left, right):
        # helper function: merge two sorted lists into one
        result = []
        i = j = 0
        while i < len(left) and j < len(right):
            if left[i] <= right[j]:
                result.append(left[i])
                i += 1
            else:
                result.append(right[j])
                j += 1
        result.extend(left[i:])
        result.extend(right[j:])
        return result
    
    def _merge_sort(self, lst):
        # helper function: recursively sort a list

        # base case: list of 0 or 1 elements is already sorted
        if len(lst) <= 1:
            return lst
        
        # split in half
        mid = len(lst) // 2
        left = lst[:mid]
        right = lst[mid:]

        # recursively sort each half and merge
        return self._merge(self._merge_sort(left), self._merge_sort(right))

    def get_runs(self):
        """Returns the current leaderboard.

        Leaderboard is given in rank order, tie-broken by runner name.

        Returns:
            The current leaderboard as a list of (time, name) pairs.
        """
        return self.runs

    def submit_run(self, time, name):
        """Adds the given run to the leaderboard

        Args:
            time: The run time in seconds.
            name: The runner's name.
        """
        lo = 0
        hi = len(self.runs)
        
        while lo < hi:
            mid = (lo + hi) // 2
            if self.runs[mid] <= (time, name):
                lo = mid + 1  # new run goes to the right of mid
            else:
                hi = mid      # new run goes to the left of mid
        
        self.runs.insert(lo, (time, name))

    def get_rank_time(self, rank):
        """Get the time required to achieve at least a given rank.

        For example, `get_rank_time(5)` will give the maximum possible time
        that would be ranked fifth.

        Args:
            rank: The rank to look up.

        Returns:
            The time required to place `rank`th or better.
        """
        # finds the result associatied with the given rank
        # uses - 1 due to 0-indexing
        # then the [0] finds the time element from that tuple
        return self.runs[rank - 1][0]
    
    def get_possible_rank(self, time):
        """Determine what rank the run would get if it was submitted.

        Does not actually submit the run.

        Args:
            time: The run time in seconds.

        Returns:
            The rank this run would be if it were to be submitted.
        """
        # uses same binary search but for time comparison
        lo = 0
        hi = len(self.runs)
        
        while lo < hi:
            mid = (lo + hi) // 2
            if self.runs[mid][0] < time:
                lo = mid + 1
            else:
                hi = mid
        
        return lo + 1  # +1 because ranks are 1-indexed

    def count_time(self, time):
        """Count the number of runs with the given time.

        Args:
            time: The run time to count, in seconds.

        Returns:
            The number of submitted runs with that time.
        """
        # find left boundary: first index where time == target
        lo = 0
        hi = len(self.runs)
        while lo < hi:
            mid = (lo + hi) // 2
            if self.runs[mid][0] < time:
                lo = mid + 1
            else:
                hi = mid
        left = lo

        # find right boundary: first index where time > target
        lo = 0
        hi = len(self.runs)
        while lo < hi:
            mid = (lo + hi) // 2
            if self.runs[mid][0] <= time:
                lo = mid + 1
            else:
                hi = mid
        right = lo

        return right - left
