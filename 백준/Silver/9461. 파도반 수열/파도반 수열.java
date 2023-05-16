import java.io.*;
import java.util.*;

public class Main {

    static long[] array = new long[101];
    static int count = 6;

    public static Long getP(int N) {
        if (array[N] != 0) {
            return array[N];
        }

        for (int i = count; i <= N; i++) {
            array[i] = array[i - 5] + array[i - 1];
//            System.out.println(array[i]);
        }
        count = N;

        return array[N];
    }

    /**
     1  * 1
     2  * 1
     3  * 1
     4  * 2
     5  * 2
     6  * 3  (`1 + `5)
     7  * 4  (`2 + `6)
     8  * 5  (`3 + `7)
     9  * 7  (`4 + `8)
     10 * 9  (`5 + `9)
     11 * 12 (`6 + `10)
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        array[0] = -1L;
        array[1] = 1L;
        array[2] = 1L;
        array[3] = 1L;
        array[4] = 2L;
        array[5] = 2L;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(getP(N));
        }
    }
}