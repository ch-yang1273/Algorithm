import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] array = new int[15];
    static int count = 0;

    static void calc(int depth) {

        if (depth == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            array[depth] = i;
            if (canSelect(depth)) {
                calc(depth + 1);
            }
        }
    }

    static boolean canSelect(int y) {
        for (int i = 0; i < y; i++) {
            if (array[y] == array[i]) {
                return false;
            }

            if (Math.abs(y - i) == Math.abs(array[y] - array[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N x N 체스판
        // 퀸은 좌우, 상하, 대각 방향으로 이동이 가능
        // N 개의 퀸을 서로 공격 할 수 없게 놓아야 한다.
        // 퀸을 놓는 경우의 수

        // 해당 위치에 두면 못 두는 위치 막는 함수
        // 같은 x, 같은 y, 대각
        // 한 depth 당 하나씩 선택되어야 한다.

        N = Integer.parseInt(br.readLine());

        calc(0);

        System.out.println(count);
    }

    static boolean blockDiagonal(int ty, int tx, int y, int x) {
        // 하강 대각
        int min = Math.min(ty, tx);
        int max = Math.max(ty, tx);
        for (int i = 0; i < (N-max+min); i++) {
            if ((ty-min+i) == y) {
                if ((tx-min+i) == x) return false;
            }

        }

        // 상승 대각
        min = Math.min(N-1-ty, tx);
        max = Math.max(N-1-ty, tx);
        for (int i = 0; i < (N-max+min); i++) {
            if ((ty + min - i) == y) {
                if ((tx - min + i) == x) return false;
            }
        }

        return true;
    }
}