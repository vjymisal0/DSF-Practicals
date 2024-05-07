import java.util.*;

class Graph {
    private int vertices;
    private LinkedList<Integer>[] adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
        // For undirected graph, add this line:
        // adjacencyList[destination].add(source);
    }

    public void display() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + " -> ");
            for (int neighbour : adjacencyList[i]) {
                System.out.print(neighbour + " ");
            }
            System.out.println();
        }
    }

    public void bfsTraversal(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.print("BFS Traversal from vertex " + startVertex + ": ");
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");
            for (int neighbour : adjacencyList[currentVertex]) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    public void dfsTraversal(int startVertex) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal from vertex " + startVertex + ": ");
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    private void dfsRecursive(int currentVertex, boolean[] visited) {
        visited[currentVertex] = true;
        System.out.print(currentVertex + " ");
        for (int neighbour : adjacencyList[currentVertex]) {
            if (!visited[neighbour]) {
                dfsRecursive(neighbour, visited);
            }
        }
    }
}

public class Practice1 {
    public static void main(String[] args) {
        int numVertices = 6;
        Graph graph = new Graph(numVertices);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);

        graph.display();

        graph.bfsTraversal(0);
        graph.dfsTraversal(0);
    }
}
