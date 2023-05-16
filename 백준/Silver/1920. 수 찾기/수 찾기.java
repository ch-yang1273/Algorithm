import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    Map<Integer, Integer> map = new HashMap<>();

    String[] strings = br.readLine().split(" ");
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(strings[i]);
      map.put(num, 1);
    }

    int T = Integer.parseInt(br.readLine());
    strings = br.readLine().split(" ");
    for (int i = 0; i < T; i++) {
      int target = Integer.parseInt(strings[i]);
      Integer integer = map.get((Integer) target);
      bw.write(((integer != null)?1:0) + "\n");
      //System.out.println((integer != null)?1:0);
    }

    bw.flush();
    bw.close();
    br.close();
  }
}