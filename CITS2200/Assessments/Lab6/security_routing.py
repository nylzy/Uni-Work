# Name: Thomas Nylund
# Student Number: 22708177

from enum import IntEnum
import heapq


class Clearance(IntEnum):
    NONE = 0
    RED = 1
    BLUE = 2
    GREEN = 3


def security_route(stations, segments, source, target):
    """Finds the fastest route from source station to target station.

    You start with no security clearance.
    When at a security station, you may choose to set your clearance to the same
    as that of the station.
    Each segment gives how long it takes to get from one station to another, and
    what clearance is required to be able to take that segment.

    Target Complexity: O(N lg N) in the size of the input (stations + segments).

    Args:
        stations: A list of what clearance is available at each station, or
            `NONE` if that station can not grant any clearance.
        segments: A list of `(u, v, t, c)` tuples, each representing a segment
            from `stations[u]` to `stations[v]` taking time `t` and requiring
            clearance `c` (`c` may be `NONE` if no clearance is required).
        source: The index of the station from which we start.
        target: The index of the station we are trying to reach.

    Returns:
        The minimum length of time required to get from `source` to `target`, or
        `None` if no route exists.
    """

    # we need to track both location and current clearance as our state,
    # since the same station reached with different clearances opens different paths.
    # so instead of N nodes we use N*4 nodes, one per (station, clearance) pair.
    # clearances are colours not a hierarchy - a RED door needs RED specifically.

    NUM_CLEARANCES = 4

    def encode(station, cl):
        return station * NUM_CLEARANCES + int(cl)

    n = len(stations)
    total_nodes = n * NUM_CLEARANCES

    adj = [dict() for _ in range(total_nodes)]

    # traversal edges: for each segment, add an edge for each clearance value
    # that satisfies the requirement (NONE means anyone can pass)
    for (u, v, t, req) in segments:
        for cl in Clearance:
            if req == Clearance.NONE or cl == req:
                src = encode(u, cl)
                dst = encode(v, cl)
                if dst not in adj[src] or t < adj[src][dst]:
                    adj[src][dst] = t

    # pickup edges: at a station with a colour you can swap to it for free
    for v in range(n):
        station_clearance = stations[v]
        if station_clearance != Clearance.NONE:
            for cl in Clearance:
                if cl != station_clearance:
                    src = encode(v, cl)
                    dst = encode(v, station_clearance)
                    if dst not in adj[src]:
                        adj[src][dst] = 0

    # dijkstra on the expanded graph, starting at (source, NONE)
    INF = float('inf')
    dist = [INF] * total_nodes
    dist[encode(source, Clearance.NONE)] = 0
    pq = [(0, encode(source, Clearance.NONE))]

    while pq:
        d, u = heapq.heappop(pq)
        if d > dist[u]:
            continue
        for v, weight in adj[u].items():
            if d + weight < dist[v]:
                dist[v] = d + weight
                heapq.heappush(pq, (dist[v], v))

    # return the fastest arrival at target regardless of final clearance
    best = min(dist[encode(target, cl)] for cl in Clearance)
    return None if best == INF else best