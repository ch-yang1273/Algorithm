import java.io.*;
import java.util.*;

public class Main {

    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static List<Item> items = new ArrayList<>();
    static int N;
    static int K;

    static int[][] vTable;
    static int wMax = 0;

    static void calc() {
        for (int i = 0; i < N; i++) { // item 인덱스
            Item item = items.get(i);
//            System.out.printf("\n %3d: ", i);
            for (int j = 1; j <= K; j++) { // 무게 k
                // 넣을 수 있나?
                if (item.weight <= j) {
                    // 첫번째 로우는 넣을  수 있으면 넣는다.
                    if (i == 0) {
                        vTable[i][j] = item.value;
//                        System.out.printf("%3d", vTable[i][j]);
                        continue;
                    }

                    // 넣는 것이 이득인가?
                    // 비교는 같은 컬럼에, 하나 뺀 로우와 비교
                    int comp1 = item.value + vTable[i - 1][j - item.weight];
                    int comp2 = vTable[i - 1][j];

                    vTable[i][j] = Math.max(comp1, comp2);
                } else {
                    // 넣을 수 없으면 이전 값 그대로 or 0
                    vTable[i][j] = (i != 0)?vTable[i-1][j]:0;
                }
//                System.out.printf("%3d", vTable[i][j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 배낭을 잘 싸보자
        // N 개의 물건
        // 무게 W 가치 V
        // 최대 무게 K
        // -> 가치의 최댓값

        // 첫줄 N, K
        String[] strings = br.readLine().split(" ");
        N = Integer.parseInt(strings[0]);
        K = Integer.parseInt(strings[1]);

        vTable = new int[N][K + 1];

        // N 개의 물품 목록
        for (int i = 0; i < N; i++) {
            strings = br.readLine().split(" ");
            int w = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);
            items.add(new Item(w, v));

            wMax = Math.max(wMax, w);
        }

        calc();

        int calcMax = 0;
        for (int i = 0; i < N; i++) {
            calcMax = Math.max(calcMax, vTable[i][K]);
        }

        System.out.println(calcMax);
    }
}