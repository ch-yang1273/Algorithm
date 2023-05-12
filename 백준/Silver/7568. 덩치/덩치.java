import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력이 얼마 없으니 브루트포스
        int T = Integer.parseInt(br.readLine());
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            String[] strings = br.readLine().split(" ");
            int x = Integer.parseInt(strings[0]);
            int y = Integer.parseInt(strings[1]);

            list.add(new Person(x, y));
        }

        for (int j = 0; j < list.size(); j++) {
            for (int j2 = 0; j2 < list.size(); j2++) {
                if (j == j2) { continue; }

                if(list.get(j).isBigger(list.get(j2))){
                    list.get(j).count++;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).count + " ");
        }
    }
}

class Person {
        int x; // 몸무게
        int y; // 키
        int count;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
            count = 1;
        }

        boolean isBigger(Person p) {
            return (x < p.x && y < p.y);
        }
}