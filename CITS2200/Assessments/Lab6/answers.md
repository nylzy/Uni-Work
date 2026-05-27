# CITS2200 Lab 6: Tranes and Planes, Security Routing

Name: Thomas Nylund

Student Number: 22708177


## Question 1 (3 marks)
Implement your solution by filling out the method stub in `trains_planes.py`.
Your implementation must pass the tests given in `test_trains_planes.py`, which can be invoked by running `python -m unittest test_trains_planes`.

See `trains_planes.py`.


## Question 2 (1 mark)
Give an argument for the correctness of your `trains_planes()` function.

- The solution uses a Union-Find to track which cities are rail-connected. Each city starts in its own group, and when a rail line opens between two cities their groups get merged. Two cities end up in the same group if and only if there is some rail path between them.
- Both lists are sorted by date first. Then we sweep through flights in order, and before checking each flight we add every rail line with a date on or before the flight date. So the Union-Find always reflects exactly which lines exist on that day. A flight is replaceable if and only if its two cities share a root, which is exactly what the problem asks for.


## Question 3 (1 mark)
Give an argument for the complexity of your `trains_planes()` function.

- Let N = len(trains) + len(planes). Sorting both lists is O(N lg N). The sweep through flights is O(N) total since the train pointer only moves forward. Each union and find call is O(a(N)) due to path compression and union by rank, which is essentially constant. So the overall complexity is O(N lg N).


## Question 1 (3 marks)
Implement your solution by filling out the method stub in `security_routing.py`.
Your implementation must pass the tests given in `test_security_routing.py`, which can be invoked by running `python -m unittest test_security_routing`.

See `security_routing.py`.


## Question 2 (1 mark)
Give an argument for the correctness of your `security_route()` function.

- The main insight is that reaching the same station with different clearances are different situations, since your clearance determines what segments you can use from there. So instead of N nodes we use N*4 nodes, one per (station, clearance) pair.
- Clearances are colours not a ranking, so a segment requiring RED needs exactly RED. A traversal edge from (u, cl) to (v, cl) is only added if the segment requirement is NONE or matches cl exactly. At stations that grant a colour we add a free edge to the same station with that colour, which models choosing to pick it up.
- Dijkstra on this graph is correct since all weights are non-negative and the state space captures every possible decision. The answer is the minimum distance to (target, any clearance).


## Question 3 (1 mark)
Give an argument for the complexity of your `security_route()` function.

- Let N = len(stations) + len(segments). The expanded graph has N*4 = O(N) nodes. Each segment creates at most 4 traversal edges and each station creates at most 3 pickup edges, so total edges are O(N). Dijkstra with a binary heap runs in O((V + E) lg V), which with V = O(N) and E = O(N) gives O(N lg N).
