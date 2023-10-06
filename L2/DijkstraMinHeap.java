import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraMinHeap {
    public static void findShortestPaths(int numberOfVertices, ArrayList<ArrayList<Node>> graph, int source) {
        int[] distances = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }

        distances[source] = 0;

        PriorityQueue<Node> minHeap = new PriorityQueue<>((node1, node2) -> node1.getCost() - node2.getCost());
        minHeap.add(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            Node currentNode = minHeap.poll();

            for (Node neighbor : graph.get(currentNode.getVertex())) {
                int potentialDistance = distances[currentNode.getVertex()] + neighbor.getCost();
                if (potentialDistance < distances[neighbor.getVertex()]) {
                    distances[neighbor.getVertex()] = potentialDistance;
                    minHeap.add(new Node(neighbor.getVertex(), potentialDistance));
                }
            }
        }

        for (int vertex = 0; vertex < numberOfVertices; vertex++) {
            System.out.println("Vertex: " + vertex + " Minimum Distance from Source: " + distances[vertex]);
        }
    }

    public static void main(String[] args) {
        int numberOfVertices = 100;
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        int[][] adjacencyMatrix = RandomArray.randomSquareArray(numberOfVertices, -5, 10);
        RandomArray.print2DArray(adjacencyMatrix);

        int source = 0;

        RandomArray.to2DGraph(adjacencyMatrix, graph);

        long startTime = System.nanoTime();
        findShortestPaths(numberOfVertices, graph, source);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // in milliseconds
        System.out.println("Duration: " + duration + "ms");
    }
}
