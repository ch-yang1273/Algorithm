import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 절단기 높이 H
        // H 높이 만큼 나무 자름

        String[] strings = br.readLine().split(" ");
        long N = Integer.parseInt(strings[0]); // 나무의 수
        long M = Integer.parseInt(strings[1]); // 가져가려는 나무 길이

        strings = br.readLine().split(" ");
        List<Long> list = new ArrayList<>();
        long max = 0;
        for (int i = 0; i < N; i++) {
            long num = Integer.parseInt(strings[i]);
            max = Math.max(max, num);
            list.add(num);
        }

        long result = binarySearch(list, M, 0, max);

        System.out.println(result);
    }

    public static long binarySearch(List<Long> list, long target, long start, long end) {
        if (start > end) {
            return 0;
        }
        
        long mid = (start + end) / 2;
        
        long value = sum(list, mid);
        if (target == value) {
            return mid;
        } else if (target > value) {
            return binarySearch(list, target, start, mid - 1);
        } else {
            // target < value
            if (target > sum(list, mid + 1)) {
                return mid;
            } else {
                return binarySearch(list, target, mid + 1, end);
            }
        }
    }

    // 해당 높이에서 가져가는 나무
    public static long sum(List<Long> list, long H) {
        long result = 0;
        for (int i = 0; i < list.size(); i++) {
            long temp = list.get(i);
            if(temp > H) {
                result += temp - H;
            }
        }

        // System.out.println("H=" + H + " sum=" + result);
        return result;
    }
}