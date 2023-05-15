import java.io.*;
import java.util.*;

public class Main {

    static int MAX_N = 100 + 1;
    static int N;
    static int M;

    static int[][] matirx = new int[MAX_N][MAX_N];
    static boolean[] visited = new boolean[MAX_N];

    static void adjacent(int u, int v, int value) {
        matirx[u][v] = value;
        matirx[v][u] = value;
    }

    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();

        visited[node] = true;
        queue.add(node);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next = 1; next <= N; next++) {
                if (!visited[next] && matirx[curr][next] == 1) {

                    // matirx에 관계 단계를 적음
                    // 바로 친구는 1에서 1되니까 괜찮음 (matirx[node][node] + 1 임)
                    adjacent(node, next, matirx[node][curr] + 1);
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        // for (int i = 1; i <= N; i++) {
        //     for (int j = 1; j <= N; j++) {
        //         System.out.print(matirx[i][j] + " ");
        //     }
        //     System.out.println("");
        // }
        // System.out.println("");
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 첫째 줄 : N(유저 수), M(친구 관계 수)
        // M개 줄 : A B (친구 관계이니 당연히 양방향)
        // 케빈 베이컨 수가 가장 작은 사람을 출력, 여러 명이면 번호가 작은 사람

        // 최단 거리니까 BFS
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        for (int i = 0; i < M; i++) {
            s = br.readLine().split(" ");

            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);

            adjacent(u, v, 1);
        }

        for (int i = 1; i <= N; i++) {
            bfs(i);
            Arrays.fill(visited, false);
        }

        int result = 0;
        int point = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += matirx[i][j];
            }

            if (point > sum) {
                point = sum;
                result = i;
            }
        }

        System.out.println(result);
    }    
}