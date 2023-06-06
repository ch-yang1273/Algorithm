import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2n 개의 스티커를 2행 n열로 배치
        // 스티커를 떼면 [왼쪽, 오른쪽, 위, 아래]의 스티커는 사용할 수 없게 된다.
        // dp인가? 왼쪽부터 쭉 계산해가면 되겠다.

        /*
        1
          O X 'O'
          X O X
        2
          X X 'O'
          O X X

        - 'O'를 선택 했을 때 가능한 케이스는 두가지 뿐이다.
        - 현재 위치([0][n])를 선택했을 때 최대 값을 구하려면 [1][n-1], [1][n-2] 값 중 큰값을 보면 되겠다.
        - [0][n] = Math.max([1][n-1], [1][n-2])
         */

        // 줄 단위로 줘서 이거는 그냥 메모리 다 써서 받아야겠다.
        int[][] array = new int[2][100_000];
        int[][] calc = new int[2][100_000];
        int j = 0;
        int k = 0;

        int T = Integer.parseInt(br.readLine());
        // 스티커 점수 배열 받기
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            for (j = 0; j < 2; j++) {
                String[] strings = br.readLine().split(" ");
                for (k = 0; k < N; k++) {
                    array[j][k] = Integer.parseInt(strings[k]);
                }
            }

            calc[0][0] = array[0][0];
            calc[1][0] = array[1][0];
            if (N == 1) {
                System.out.println(Math.max(calc[0][0], calc[1][0]));
                continue;
            }

            calc[0][1] = calc[1][0] + array[0][1];
            calc[1][1] = calc[0][0] + array[1][1];
            if (N == 2) {
                System.out.println(Math.max(calc[0][1], calc[1][1]));
                continue;
            }

            int max;
            for (k = 2; k < N; k++) {
                for (j = 0; j < 2; j++) {
                    max = Math.max(calc[(j==0)?1:0][k-1], calc[(j==0)?1:0][k-2]);
                    calc[j][k] = max + array[j][k];

//                    System.out.println("(" + j + "," + k + ")=" + calc[j][k]);
                }
            }

            System.out.println(Math.max(calc[0][k-1], calc[1][k-1]));
        }
    }
}