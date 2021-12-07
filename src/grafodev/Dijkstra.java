package grafodev;

import java.util.ArrayList;

public class Dijkstra {

    static int V;

    public Dijkstra(int V) {
        Dijkstra.V = V;
    }

    static ArrayList<Integer> list = new ArrayList<Integer>();

    int minMesafe(int dist[], Boolean set[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int vertex = 0; vertex < V; vertex++) {
            if (set[vertex] == false && dist[vertex] <= min) {
                min = dist[vertex];
                min_index = vertex;
            }
        }

        return min_index;
    }

    public ArrayList<Integer> dijkstra(int graph[][], int src) {
        int dist[] = new int[V];
        Boolean set[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            set[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minMesafe(dist, set);
            set[u] = true;
            for (int vertex = 0; vertex < V; vertex++) {
                if (!set[vertex] && graph[u][vertex] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][vertex] < dist[vertex]) {
                    dist[vertex] = dist[u] + graph[u][vertex];
                }
            }
        }
        return new Yogunluk(dist).yogunluk();//grafın en yakın yol ağacını geri döndürür
    }
}
