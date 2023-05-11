import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        int decimal = Integer.parseInt(line, 16);
        System.out.println(decimal);
    }
}