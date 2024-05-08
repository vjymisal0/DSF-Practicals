import java.util.*;

class Edge implements Comparable<Edge> {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class PrimsAlgorithm {
    private int V; // Number of vertices
    private List<List<Edge>> adjList;

    public PrimsAlgorithm(int V) {
        this.V = V;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); // Graph is undirected
    }

    public void primMST() {
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        int[] key = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // Start from the first vertex
        pq.offer(new Edge(0, 0));
        key[0] = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().dest;
            visited[u] = true;

            for (Edge e : adjList.get(u)) {
                int v = e.dest;
                int weight = e.weight;

                if (!visited[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                    pq.offer(new Edge(v, weight));
                }
            }
        }

        // Print the MST
        System.out.println("Minimum Spanning Tree (MST):");
        for (int i = 1; i < V; i++) {
            System.out.println("Edge: " + parent[i] + " - " + i + ", Weight: " + key[i]);
        }
    }

    public static void main(String[] args) {
        PrimsAlgorithm graph = new PrimsAlgorithm(6);
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 7);
        graph.addEdge(0, 3, 8);
        graph.addEdge(1, 2, 9);
        graph.addEdge(1, 4, 7);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 4, 8);
        graph.addEdge(2, 5, 7);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 5);

        graph.primMST();
    }
}
