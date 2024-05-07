import numpy as np

class Graph:
    def __init__(self, vertices):
        self.V = vertices
        self.graph = np.zeros((vertices, vertices))

    def add_edge(self, src, dest, weight):
        self.graph[src][dest] = weight
        self.graph[dest][src] = weight

    def min_distance(self, dist, visited):
        min_dist = float('inf')
        min_index = -1
        for v in range(self.V):
            if dist[v] < min_dist and not visited[v]:
                min_dist = dist[v]
                min_index = v
        return min_index

    def dijkstra(self, src):
        dist = [float('inf')] * self.V
        dist[src] = 0
        visited = [False] * self.V

        for _ in range(self.V):
            u = self.min_distance(dist, visited)
            visited[u] = True
            for v in range(self.V):
                if self.graph[u][v] > 0 and not visited[v] and dist[v] > dist[u] + self.graph[u][v]:
                    dist[v] = dist[u] + self.graph[u][v]

        return dist

def main():
    landmarks = ["katraj", "iskcon", "kondhwa", "viit_college"]
    num_landmarks = len(landmarks)

    graph = Graph(num_landmarks)
    graph.add_edge(0, 1, 10)  # katraj - iskcon
    graph.add_edge(0, 2, 15)  # katraj - kondhwa
    graph.add_edge(0, 3, 20)  # katraj - viit_college
    graph.add_edge(1, 2, 35)  # iskcon - kondhwa
    graph.add_edge(1, 3, 25)  # iskcon - viit_college
    graph.add_edge(2, 3, 30)  # kondhwa - viit_college

    source = 3  # viit_college

    shortest_distances = graph.dijkstra(source)

    for i, distance in enumerate(shortest_distances):
        print(f"Minimum distance from {landmarks[source]} to {landmarks[i]} is {distance}")

if __name__ == "__main__":
    main()
