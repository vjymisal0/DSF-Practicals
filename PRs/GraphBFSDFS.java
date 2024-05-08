import java.util.*;

// Class representing a graph
class GraphBFSDFS {
    private int V; // Number of vertices
    private LinkedList<Integer> adj[]; // Adjacency list representation

    // Constructor
    @SuppressWarnings("unchecked")
    GraphBFSDFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    // Function to add an edge to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // Function to perform BFS traversal
    void BFS(int s) {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);
        System.out.print("BFS Traversal from vertex " + s + ": ");
        // while (queue.size() != 0) {
        // // Dequeue a vertex from queue and print it
        // s = queue.poll();
        // System.out.print(s + "->");

        // Get all adjacent vertices of the dequeued vertex s. If an adjacent has not
        // been visited, then mark it
        // visited and enqueue it
        // Iterator<Integer> i = adj[s].listIterator();
        // while (i.hasNext()) {
        // int n = i.next();
        // if (!visited[n]) {
        // visited[n] = true;
        // queue.add(n);
        // }
        // }
        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + "->");
            for (int neighbour : adj[currentVertex]) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        // }
    }

    // Function to perform DFS traversal
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;

        System.out.print(v + "->");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Function to perform DFS traversal starting from vertex v
    void DFS(int v) {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        System.out.print("\nDFS traversal from vertex " + v + " :");
        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

    public static void main(String args[]) {
        GraphBFSDFS g = new GraphBFSDFS(5);

        // Example graph
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        g.addEdge(3, 0);
        g.addEdge(3, 4);
        g.addEdge(4, 2);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the source vertex for BFS:");
        int source = sc.nextInt();
        System.out.println();
        g.BFS(source);
        System.out.print("\n\nEnter the source vertex for DFS:");
        source = sc.nextInt();
        g.DFS(source);
    }
}
