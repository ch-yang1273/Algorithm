import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;

    static void printSequence(Stack<Integer> stack, int curr, int deep) {
        stack.push(curr);

        if (deep == M) {
            for (Integer integer : stack) {
                System.out.print(integer + " ");
            }
            System.out.println();
            stack.pop();
            return;
        }

        for (int i = curr; i <= N; i++) {
            printSequence(stack, i, deep + 1);
        }
        stack.pop();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        N = Integer.parseInt(strings[0]);
        M = Integer.parseInt(strings[1]);

        Stack<Integer> stack = new Stack<>();

        // for문으로 하면 반복이 늘어나서, 재귀로 푸는 것이 좋을 듯하다. 깊이 주고
        for (int i = 1; i <= N; i++) {
            printSequence(stack, i, 1);
        }
    }
}