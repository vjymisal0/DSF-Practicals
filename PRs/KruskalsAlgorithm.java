import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalsAlgorithm {
    private int V; // Number of vertices
    private List<Edge> edges;

    public KruskalsAlgorithm(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    private void union(int[] parent, int[] rank, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);

        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;
        else if (rank[xRoot] > rank[yRoot])
            parent[yRoot] = xRoot;
        else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    public void kruskalMST() {
        List<Edge> result = new ArrayList<>();
        Collections.sort(edges);

        int[] parent = new int[V];
        int[] rank = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int e = 0;
        int i = 0;
        while (e < V - 1) {
            Edge nextEdge = edges.get(i++);
            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            if (x != y) {
                result.add(nextEdge);
                union(parent, rank, x, y);
                e++;
            }
        }

        System.out.println("Minimum Spanning Tree (MST):");
        for (Edge edge : result) {
            System.out.println((char) ('A' + edge.src) + " - " + (char) ('A' + edge.dest) + ": " + edge.weight);
        }
    }

    public static void main(String[] args) {
        KruskalsAlgorithm graph = new KruskalsAlgorithm(7);
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 5, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 5, 5);
        graph.addEdge(3, 4, 3);
        graph.addEdge(3, 6, 3);
        graph.addEdge(4, 6, 3);
        graph.addEdge(5, 6, 2);
        graph.kruskalMST();
    }
}
