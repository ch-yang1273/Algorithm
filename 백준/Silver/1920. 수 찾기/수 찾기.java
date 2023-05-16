// Binary Search 구현해서 풀기
// 다른 방법으로 푸는 것이 빠르지만, Binary Search 구현의 Base로 좋은 것 같다.
import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

  static Integer bin_search(List<Integer> list, Integer target, int start, int end) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) / 2;

    // Integer는 == 비교하면 int 값이 같아도 false가 될 수 있으니, 꼭 equals로 비교
    if (list.get(mid).equals(target)) {
      // 탐색 성공
      return mid;
    }
    else if (list.get(mid) > target) {
      // target이 작으니까 앞쪽 절반을 검색
      return bin_search(list, target, start, mid-1);
    }
    else {
      // // target이 크니까 뒤쪽 절반을 검색
      return bin_search(list, target, mid+1, end);
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    List<Integer> nList = new ArrayList<>();

    String[] strs = br.readLine().split(" ");
    for (int i = 0; i < N; i++) {
      nList.add(Integer.parseInt(strs[i]));
    }

    Collections.sort(nList);

    int M = Integer.parseInt(br.readLine());

    strs = br.readLine().split(" ");
    for (int i = 0; i < M; i++) {
      int target = Integer.parseInt(strs[i]);
      Integer index = bin_search(nList, target, 0, nList.size()-1);
      System.out.println((index!=null)?1:0);
    }
  }
}
