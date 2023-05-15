import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
  static final int MAX_N = 1001;
  static int N; //Node 의 개수
  static int E; //Edge 의 개수

  //Adjacency Matrix
  static int[][] graph = new int[MAX_N][MAX_N];
  static boolean[] visited = new boolean[MAX_N];

  static void bfs(int node) {
    Queue<Integer> myQueue = new LinkedList<>();
    visited[node] = true;

    myQueue.add(node);

    while (!myQueue.isEmpty()) {
      int curr = myQueue.poll();

      System.out.print(curr + " ");

      for (int next = 0; next <= N; next++) {
        if (!visited[next] && (graph[curr][next] != 0)) {
          visited[next] = true;
          myQueue.add(next);
        }
      }
    }
  }

  static void dfsR(int node) {
    visited[node] = true;

    System.out.print(node + " ");

    for (int next = 0; next <= N; next++) {
      if (!visited[next] && (graph[node][next] != 0)) {
        dfsR(next);
      }
    }
  }

  static void dfs(int node) {
    Stack<Integer> myStack = new Stack<>();
    myStack.push(node);

    while (!myStack.empty()) {
      int curr = myStack.pop();

      if (visited[curr]) continue;

      visited[curr] = true;
      System.out.println(curr);

      for (int next = 0; next <= N; next++) {
        if (!visited[next] && (graph[curr][next] != 0)) {
          myStack.push(next);
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] str = br.readLine().split(" ");
    N = Integer.parseInt(str[0]);
    E = Integer.parseInt(str[1]);

    int startNode = Integer.parseInt(str[2]);

    for (int i = 0; i < E; i++) {
      str = br.readLine().split(" ");

      int u = Integer.parseInt(str[0]);
      int v = Integer.parseInt(str[1]);

      graph[u][v] = 1;
      graph[v][u] = 1;
    }

    dfsR(startNode);
    System.out.println("");
    Arrays.fill(visited, false);
    bfs(startNode);
  }
}