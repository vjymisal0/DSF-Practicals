#include <iostream>
#include <string>
#define INF 1000000000
#define MAX_VERTICES 1001
using namespace std;
string vertices[MAX_VERTICES]; // Array to store vertex names
int graph[MAX_VERTICES][MAX_VERTICES]; // Assuming maximum 1000 vertices
int distances[MAX_VERTICES]; bool visited[MAX_VERTICES]; int num_vertices,
num_edges;
int extractMin() { int minDist = INF,
minIndex = -1;
for (int i = 1; i <= num_vertices; ++i) { if
(!visited[i] && distances[i] < minDist) {
minDist = distances[i]; minIndex = i;
}
}
return minIndex;
}
void dijkstra(int start) { for (int i = 1; i <=
num_vertices; ++i) { distances[i] =
INF; visited[i] = false;
}
distances[start] = 0;
for (int count = 0; count < num_vertices - 1; ++count) {
int u = extractMin();
visited[u] = true;
for (int v = 1; v <= num_vertices; ++v) { if (!visited[v] && graph[u][v] && distances[u] !=
INF && distances[u] + graph[u][v] <
distances[v]) {
distances[v] = distances[u] + graph[u][v];
}
}
}
}
void printShortestPath(string destination) { int
destIndex = -1;
for (int i = 1; i <= num_vertices; ++i) { if
(vertices[i] == destination) {
destIndex = i;
break;
}
}
if (distances[destIndex] == INF) { cout << "There is no path from thesource to the destination." << endl; return;
}
cout << "Shortest path from source to " << destination << " (distance: " <<
distances[destIndex] << "):" << endl;
string path = destination; int current = destIndex; while (current != 1) { for (int i = 1; i
<= num_vertices; ++i) { if (graph[i][current] != 0 && distances[current] == distances[i]
+ graph[i][current]) { path = vertices[i] + " -> " + path; current = i; break;
}
}
}
cout << "Source -> " << path << endl;
}
void printAdjacencyMatrix() { cout <<
"Adjacency Matrix:" << endl; for (int
i = 1; i <= num_vertices; ++i) { for
(int j = 1; j <= num_vertices; ++j) {
cout << graph[i][j] << " ";
}
cout << endl;
}
}
int main() {
cout << "Enter the number of vertices and edges: "; cin
>> num_vertices >> num_edges;
cout << "Enter the vertices:" << endl;
for (int i = 1; i <= num_vertices; ++i) {
cin >> vertices[i];
}
cout << "Enter the edges and their weights (source destination weight):" << endl;
string source, destination; int weight;
for (int i = 0; i < num_edges; ++i) {
cin >> source >> destination >> weight;
int sourceIndex = -1, destIndex = -1;
for (int j = 1; j <= num_vertices; ++j) { if
(vertices[j] == source) {
sourceIndex = j;
}
if (vertices[j] == destination) { destIndex
= j;
}
if (sourceIndex != -1 && destIndex != -1) {
break;
}
}
graph[sourceIndex][destIndex] = weight;
}
printAdjacencyMatrix();
string start_vertex, end_vertex; cout <<
"Enter the starting vertex: "; cin >>
start_vertex;
dijkstra(1);
cout <<
"Shortest distances from vertex "
<<start_vertex << ":"
<< endl;
for (int i =
1; i <=
num_vertices; ++i) {
cout << "To vertex " << vertices[i] << ": " << distances[i] << endl;
}
cout << "Enter the destination vertex: ";
cin >> end_vertex;
printShortestPath(end_vertex);
return 0;
}