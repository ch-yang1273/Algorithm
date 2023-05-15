import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_N = 1001;
    static int N;
    static int M;

    // Adjacency Matrix
    static int[][] matrix = new int[MAX_N][MAX_N];
    static boolean[] visited = new boolean[MAX_N];

    static void dfsR(int node) {
        visited[node] = true;

        System.out.print(node + " ");

        for (int next = 0; next <= N; next++) {
            if (!visited[next] && matrix[node][next] == 1) {
                dfsR(next);
            }
        }
    }

    // bfs에서 Queue에서 Stack으로만 바꾸면 먼저 방문할 노드가 나중에 출력된다.
    // 큰 수가 나중에 나오도록 먼저 집어넣고, 꺼내면서 visited 해야한다.
    // 이려면 Node가 Stack에 중복으로 들어갈 수 있으니 visited를 확인하고 방문처리
    static void dfs(int node) {
        Stack<Integer> stack = new Stack<>();

        stack.push(node);

        while (!stack.empty()) {
            int curr = stack.pop();

            if (visited[curr]) {
                continue;
            }

            visited[curr] = true;
            System.out.print(curr + " ");

            for (int next = N; next >= 0; next--) {
                if(!visited[next] && matrix[curr][next] == 1) {
                    stack.add(next);
                }
            }
        }
    }

    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();

        visited[node] = true;
        queue.add(node);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");

            for (int next = 0; next <= N; next++) {
                if(!visited[next] && matrix[curr][next] == 1) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫 줄
        // N(노드 1~N), M(간선 수), V(탐색 시작노드)
        // 다음 줄 (M)
        // a b (간선 연결, 양방향)

        String[] strings = br.readLine().split(" ");
        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);
        int start = Integer.parseInt(strings[2]);

        for (int i = 0; i < M; i++) {
            strings = br.readLine().split(" ");

            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);

            // 양방향 연결
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        dfsR(start);

        System.out.println("");
        Arrays.fill(visited, false);

        // dfs(start);

        // System.out.println("");
        // Arrays.fill(visited, false);

        bfs(start);
    }
}
