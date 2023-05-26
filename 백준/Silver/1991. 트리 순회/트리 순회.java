import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int value;
        int left;
        int right;

        public Node(int value) {
            this.value = value;
            this.left = -1;
            this.right = -1;
        }
    }

    static List<Node> list = new ArrayList<>();

    // 전위 순회 : root - left - right
    public static void preOrder(int nodeValue) {
        Node curr = list.get(nodeValue);

        // 루트
        printNode(curr.value);

        // left
        if (curr.left != -1) {
            preOrder(curr.left);
        }

        // right
        if (curr.right != -1) {
            preOrder(curr.right);
        }
    }

    // 중위 순회 : left - root - right
    public static void middleOrder(int nodeValue) {
        Node curr = list.get(nodeValue);

        // left
        if (curr.left != -1) {
            middleOrder(curr.left);
        }

        // 루트
        printNode(curr.value);

        // right
        if (curr.right != -1) {
            middleOrder(curr.right);
        }
    }

    // 후위 순회 : left - right - root
    public static void postOrder(int nodeValue) {
        Node curr = list.get(nodeValue);

        // left
        if (curr.left != -1) {
            postOrder(curr.left);
        }

        // right
        if (curr.right != -1) {
            postOrder(curr.right);
        }

        // 루트
        printNode(curr.value);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 노드 개수가 N 개
        // 관계 입력도 N 줄
        // 항상 A가 루트 노드
        int N = Integer.parseInt(br.readLine());

        // 미리 노드 생성하고 가야겠다.
        for (int i = 0; i < N; i++) {
            list.add(new Node(i)); // 'A' => 0
        }

        for (int i = 0; i < N; i++) {
            char[] charArray = br.readLine().toCharArray();
            int x = charArray[0] - 'A';
            int y = (charArray[2] == '.') ? -1 : charArray[2] - 'A';
            int z = (charArray[4] == '.') ? -1 : charArray[4] - 'A';

            list.get(x).left = y;
            list.get(x).right = z;
        }

        // 전위 순회
        preOrder(0);
        System.out.println();
        middleOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();
        br.close();
    }

    static void printNode(int value) {
        System.out.print((char)(value + 'A'));
    }
}