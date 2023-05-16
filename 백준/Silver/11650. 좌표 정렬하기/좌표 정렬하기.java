import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main
 */
public class Main {
  static class Position implements Comparable<Position> {
    int x;
    int y;

    public Position(int x, int y){
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Position o) {
      if (this.x > o.x) {
        return 1;
      }
      else if (this.x < o.x) {
        return -1;
      }
      else {
        return this.y - o.y;
      }
    }

    @Override
    public String toString() {
      return x + " " + y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    List<Position> positions = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      String[] strings = br.readLine().split(" ");
      positions.add(new Position(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])));
    }

    Collections.sort(positions);

    for (int i = 0; i < N; i++) {
      bw.write(positions.get(i) + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}