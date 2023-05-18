import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;

    static int[][] matrix;
    static int[][] matrixCopy;
    static boolean[][] visited;
    static int maxSize = 0;

    static int[] dirX = {-1, 1, 0, 0}; // 왼쪽 오른쪽 아래 위
    static int[] dirY = {0, 0, -1, 1};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(y=" + y + ",x=" + x + ")";
        }
    }

    static int calc(Stack<Point> stack, int y, int x, int count) {
        if (y < 0 || x < 0 || y >= N || x >= M) {
            return -1;
        }

        if (matrix[y][x] != 0) {
            return -1;
        }

        int result = 0;

        stack.add(new Point(x, y));
        count--;

        if (count == 0) {
            deepCopy();
            for (Point p : stack) {
                matrixCopy[p.y][p.x] = 1;
            }

            int size = spread();

            //test
//            for (Point p : stack) {
//                System.out.print(p + " =");
//            }
//            System.out.println(size);
//            if (size > maxSize) {
//                maxSize = size;
//                printMatrixCopy();
//            }

            stack.pop();
            return size;
        } else {
            for (int i = y; i < N; i++) {
                for (int j = (i == y ? x+1:0); j < M; j++) {
                    if (matrix[i][j] == 0) {
                        result = Math.max(result, calc(stack, i, j, count));
                    }
                }
            }
            stack.pop();

            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N x M 직사각형
        // 0: 빈칸, 1: 벽, 2: 바이러스
        // 바이러스는 상하좌우로 퍼진다.
        // 세울 수 있는 벽의 개수는 3개
        // 얻을 수 있는 안전 영역 크기 최댓값

        // N과 M은 3~8로 그렇게 크지 않다.
        // 칸 끼워보는 것은 브루트포스 돌리라는 것인가?

        // 일단 직사각형 받고, 바이러스 퍼트리는 코드 먼저 작성
        // 안전 영역 개수 세기
        // 빈 칸 3개 넣기 - 앞 쪽부터 3개 넣고, 뒤쪽을 하나씩 이동

        String[] strings = br.readLine().split(" ");
        N = Integer.parseInt(strings[0]); // 세로
        M = Integer.parseInt(strings[1]); // 가로

        matrix = new int[N][M];
        matrixCopy = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            strings = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(strings[j]);
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    result = Math.max(result, calc(new Stack<Point>(), i, j, 3));
                }
            }
        }

        System.out.println(result);
    }

    static int spread() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrixCopy[i][j] == 2) {
                    dfsSpread(i, j);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        return getSafeZoneSize();
    }

    // dfsSpread 처음 넣기 전에 2가 맞는지 넣어야함.
    static void dfsSpread(int y, int x) {
        if (y < 0 || x < 0 || y >= N || x >= M) {
            return;
        }

        if (visited[y][x]) {
            return;
        }
        visited[y][x] = true;

        if (matrixCopy[y][x] == 1) {
            return;
        }

        matrixCopy[y][x] = 2;
        for (int i = 0; i < 4; i++) {
            dfsSpread(y + dirY[i], x + dirX[i]);
        }
    }

    static int getSafeZoneSize() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrixCopy[i][j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    static void deepCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrixCopy[i][j] = matrix[i][j];
            }
        }
    }

    static void printMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printMatrixCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(matrixCopy[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}