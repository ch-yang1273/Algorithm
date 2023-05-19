import java.io.*;
import java.util.*;

public class Main {

    static Stack<Integer> stack = new Stack<>();
    static boolean[] isUsed;

    static int N;
    static int M;

    static void printSequence(int depth) {

        if (depth == M) {
            for (Integer num : stack) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!isUsed[i]) {
                stack.push(i);
                isUsed[i] = true;
                printSequence(depth + 1);
                stack.pop();
                isUsed[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);

        isUsed = new boolean[N+1];
        isUsed[0] = true; //0은 안쓴다.

        printSequence(0);
    }
}
