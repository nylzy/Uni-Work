# Name: Thomas Nylund
# Student Number: 22708177


def trains_planes(trains, planes):
    """Find what flights can be replaced with a rail journey.

    Initially, there are no rail connections between cities. As rail connections
    become available, we are interested in knowing what flights can be replaced
    by a rail journey, no matter how indirect the route. All rail connections
    are bidirectional.

    Target Complexity: O(N lg N) in the size of the input (trains + planes).

    Args:
        trains: A list of `(date, lcity, rcity)` tuples specifying that a rail
            connection between `lcity` and `rcity` became available on `date`.
        planes: A list of `(code, date, depart, arrive)` tuples specifying that
            there is a flight scheduled from `depart` to `arrive` on `date` with
            flight number `code`.

    Returns:
        A list of flights that could be replaced by a train journey.
    """

    parent = {}
    rank = {}

    def find(city):
        # make city its own group if we haven't seen it before
        if city not in parent:
            parent[city] = city
            rank[city] = 0
        # path compression - point straight to root to keep tree flat
        if parent[city] != city:
            parent[city] = find(parent[city])
        return parent[city]

    def union(city_a, city_b):
        root_a = find(city_a)
        root_b = find(city_b)
        if root_a == root_b:
            return
        # attach smaller tree under larger one to keep depth low
        if rank[root_a] < rank[root_b]:
            root_a, root_b = root_b, root_a
        parent[root_b] = root_a
        if rank[root_a] == rank[root_b]:
            rank[root_a] += 1

    # sort both lists by date so we can sweep through in order - O(N lg N)
    sorted_trains = sorted(trains, key=lambda t: t[0])
    sorted_planes = sorted(planes, key=lambda p: p[1])

    replaceable = []
    train_idx = 0

    for flight in sorted_planes:
        code, flight_date, depart, arrive = flight

        # add all rail lines that opened on or before this flight's date
        while train_idx < len(sorted_trains) and sorted_trains[train_idx][0] <= flight_date:
            _, lcity, rcity = sorted_trains[train_idx]
            union(lcity, rcity)
            train_idx += 1

        # flight is replaceable if both cities are in the same connected component
        if depart != arrive and find(depart) == find(arrive):
            replaceable.append(flight)

    return replaceable