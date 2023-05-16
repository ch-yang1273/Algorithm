import java.io.*;
import java.util.*;

public class Main {

    static int[][] array;
    static boolean[][] visited;
    static int N;
    // 상하좌우가 인접
    static int[] dirX = {-1, 1, 0, 0}; // 왼쪽, 오른쪽, 아래, 위
    static int[] dirY = {0, 0, -1, 1};

    static boolean isAbsMode = false;

    public static int dfs(int x, int y, int target) {
        // 범위 밖
        if (x >= N || y >= N || x < 0 || y < 0) {
//            System.out.println("out 1 x=" + x +", y=" + y);
            return 0;
        }

        int temp = array[y][x];
        if (isAbsMode) {
            temp = Math.abs(temp);
        }

        if (visited[y][x] || temp != target) {
//            System.out.println("out 2 x=" + x +", y=" + y);
            return 0;
        }

//        System.out.println("visit x=" + x +", y=" + y);
        visited[y][x] = true;

        int sum = 1;
        for (int i = 0; i < 4; i++) {
            sum += dfs(x + dirX[i], y + dirY[i], target);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N x N 칸
        // DFS나 BFS로 범위 찾는 문제로 보임
        // R=-1, G=1, B=0

        N = Integer.parseInt(br.readLine());
        array = new int[N][N]; //[y][x]
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                int temp;
                if (chars[j] == 'R') { temp = -1; }
                else if (chars[j] == 'G') { temp = 1; }
                else { temp = 0; }

                array[i][j] = temp;
            }
        }

//        printArray(N);
//        System.out.println("");

        int count = 0;

        for (int target = -1; target <= 1; target++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dfs(j, i, target) > 0) {
                        count++;
                    }
                }
            }
        }

        System.out.print(count + " ");

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        isAbsMode = true;
        count = 0;
        for (int target = 0; target <= 1; target++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dfs(j, i, target) > 0) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    static void printArray(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void printVisited(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print( (visited[i][j]?1:0) + " ");
            }
            System.out.println();
        }
    }
}