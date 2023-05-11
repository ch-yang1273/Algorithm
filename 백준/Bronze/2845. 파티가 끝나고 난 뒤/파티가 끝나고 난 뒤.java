import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int participate = Integer.parseInt(strings[0]) * Integer.parseInt(strings[1]);
        
        strings = br.readLine().split(" ");
        for (int i = 0; i < strings.length; i++) {
            int temp = Integer.parseInt(strings[i]);
            System.out.print( (temp - participate) + " ");
        }
    }
}