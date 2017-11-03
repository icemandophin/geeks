public static void BFS(GraphNode node) {
    Queue<GraphNode> queue = new LinkedList<GraphNode>();
    node.visited = true;
    queue.add(node);
    System.out.println(node.value);
    while(!queue.isEmpty()) {
        GraphNode v = queue.poll();
        for(GraphNode w : v.neighbors) {
            if(!w.visited) {
                System.out.println(w.value);
                w.visited = true;
                queue.add(w);
            }
        }
    }
}

public static void DFS(GraphNode node) {
    Stack<GraphNode> stack = new Stack<GraphNode>();
    stack.push(node);
    while(!stack.isEmpty()) {
        GraphNode v = stack.pop();
        if(!v.visited) {
            System.out.println(v.value);
            v.visited = true;
            for(int i = v.neighbors.length - 1; i >= 0; i--) {
                stack.push(v.neighbors[i]);
            }
        }
    }
}

public class Dijkstra {
// Dijkstra's algorithm to find shortest path from s to all other nodes
    public static int [] dijkstra (WeightedGraph G, int s) {
       final int [] dist = new int [G.size()];  // shortest known distance from "s"
       final int [] pred = new int [G.size()];  // preceeding node in path
       final boolean [] visited = new boolean [G.size()]; // all false initially

        for (int i=0; i<dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;

        for (int i=0; i<dist.length; i++) {
           final int next = minVertex (dist, visited);
           visited[next] = true;

          // The shortest path to next is dist[next] and via pred[next].
          final int [] n = G.neighbors (next);
          for (int j=0; j<n.length; j++) {
             final int v = n[j];
             final int d = dist[next] + G.getWeight(next,v);
             if (dist[v] > d) {
                dist[v] = d;
                pred[v] = next;
             }
          }
       }
       return pred;  // (ignore pred[s]==0!)
    }
    // find closet vertex that haven't visited
    private static int minVertex (int [] dist, boolean [] v) {
       int x = Integer.MAX_VALUE;
       int y = -1;   // graph not connected, or no unvisited vertices
       for (int i=0; i<dist.length; i++) {
          if (!v[i] && dist[i]<x) {y=i; x=dist[i];}
       }
       return y;
    }
}
