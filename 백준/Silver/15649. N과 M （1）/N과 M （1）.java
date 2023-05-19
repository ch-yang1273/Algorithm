import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

  static List<Integer> list = new ArrayList<>();
  static boolean[] used;

  static void printSequence(int currNum, int maxN, int maxM, int depth) {

    list.add(currNum);
    used[currNum] = true;

    if (depth == (maxM - 1)) {
      for (int i = 0; i < list.size(); i++) {
        System.out.print(list.get(i) + " ");
      }
      System.out.println("");
    } else {

      for (int i = 1; i <= maxN; i++) {
        if (!used[i]) {
          printSequence(i, maxN, maxM,depth + 1);
        }
      }
    }

    list.remove(depth);
    used[currNum] = false;
  }

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] strings = br.readLine().split(" ");

    int N = Integer.parseInt(strings[0]);
    int M = Integer.parseInt(strings[1]);

    used = new boolean[N + 1];
    used[0] = true; //0은 쓰지말고
    for (int i = 1; i <= N; i++) {
      printSequence(i, N, M, 0);
    }
  }
}