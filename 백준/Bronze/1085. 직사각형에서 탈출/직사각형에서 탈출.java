import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");

        int x = Integer.parseInt(strings[0]);
        int y = Integer.parseInt(strings[1]);
        int w = Integer.parseInt(strings[2]);
        int h = Integer.parseInt(strings[3]);

        int verticalMin = Math.min(x, w-x);
        int horizontalMin = Math.min(y, h-y);

        System.out.println(Math.min(verticalMin, horizontalMin));
    }
}