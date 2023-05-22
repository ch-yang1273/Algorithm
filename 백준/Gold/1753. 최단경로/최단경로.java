import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int id;
        int weight;

        public Edge(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static HashMap<Integer, Integer>[] adj;
    static int V;
    static int E;
    static int startNode;

    static int[] dist;
    static boolean[] visited;

    /** 다익스트라 최단 거리 알고리즘
     * BFS는 가중치가 있는 그래프 탐색에는 적합하지 않다.
     * 다익스트라를 사용하면, 한 지점에서 다른 모든 지점까지의 최단거리를 구할 수 있다.
     * 매 상황에서 가장 비용이 적은 노드를 선택해, 그리디 알고리즘으로 분류
     * dist 배열 : 각 정점들까지의 최단거리를 저장
     *   - 시작 노드는 0, 그 외의 노드는 무한대만큼의 큰 값
     * visit 배열 : 각 정점들의 방문 여부
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        V = Integer.parseInt(strings[0]);
        E = Integer.parseInt(strings[1]);

        adj = new HashMap[V + 1];
        dist = new int[V + 1];
        visited = new boolean[V + 1];

        startNode = Integer.parseInt(br.readLine());

        for (int i = 1; i < V + 1; i++) {
            adj[i] = new HashMap<>();
        }

        // 인접 관계 및 가중치 입력
        for (int i = 0; i < E; i++) {
            strings = br.readLine().split(" ");
            int src = Integer.parseInt(strings[0]);
            int dest = Integer.parseInt(strings[1]);
            int weight = Integer.parseInt(strings[2]);
            setAdjacent(src, dest, weight);
        }

        // dist 초기화 (시작 노드는 0, 그 외의 노드는 무한대만큼의 큰 값)
        // startNode와 직접 연결이 있는 경우 dist 설정
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;
//        for (int dest : adj[startNode].keySet()) {
//            dist[dest] = adj[startNode].get(dest);
//        }

        dijkstra();

        for (int i = 1; i < V + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    /**
     * 방문하지 않은 노드 중 최단 거리가 가장 짧은 노드를 선택
     * 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하고, 최단 거리 테이블을 갱신
     * 해당 노드를 거쳐 다른 노드로 가는 비용을 계산하고, 최단 거리 테이블을 갱신
     * 반복
     */
    static void dijkstra() {
        PriorityQueue<Edge> pQueue = new PriorityQueue<>();
        pQueue.add(new Edge(startNode, 0));

        while (!pQueue.isEmpty()) {
            Edge curr = pQueue.poll();
//            System.out.println("curr=" + curr.id);

            if (curr.weight > dist[curr.id]) continue;

            for (int dest : adj[curr.id].keySet()) {
                int weight = adj[curr.id].get(dest);

                if (dist[dest] > dist[curr.id] + weight) {
                    dist[dest] = dist[curr.id] + weight;
                    pQueue.add(new Edge(dest, dist[dest]));
                }
            }
        }
    }

    static void setAdjacent(int src, int dest, int weight) {
        if (!adj[src].containsKey(dest)) {
            adj[src].put(dest, weight);
        } else if (adj[src].get(dest) > weight) {
            adj[src].put(dest, weight);
        }
    }
}