import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 첫 줄에 N
        // 다음부터 1~N까지 정수를 임의의 순서로 준다. (타겟 수열)

        // 스택에 1~N 까지 순서대로 줬을 때,
        // push(+)와 pop(-)을 적절히 조합해서
        // 위의 타겟 수열을 만들어야 한다.

        // 불가능하면 NO 출력

        // 쉽다. pop 될 숫자가 출력되야 할 순서가 맞으면 pop하고, 아니면 push
        // N 까지 다 push해보고 안되면 NO 출력하면 되겠다.
        // bw 사용해서 성공하면 flush
        
        // bw 사용하면 버퍼가 다 차서 출력을 해버린다. (출력 초과)
        // sb로 바꾸니 성공

        int N = Integer.parseInt(br.readLine());
        List<Integer> targetList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            targetList.add(Integer.parseInt(br.readLine()));
        }
        targetList.add(-1);

        Stack<Integer> stack = new Stack<>();
        int count = 0;
        stack.push(-2);
        for (int i = 1; i <= N; i++) {
            while(stack.peek().equals(targetList.get(count))) {
                stack.pop();
                sb.append("-\n");
                // bw.write("-\n");
                count++;
            }

            stack.push(i);
            sb.append("+\n");
            // bw.write("+\n");
        }

        while(stack.peek().equals(targetList.get(count))) {
            sb.append("-\n");
            // bw.write("-\n");
            stack.pop();
            count++;
        }
        
        if(count == N) {
            System.out.println(sb);
            // bw.flush();
        } else {
            System.out.println("NO");
        }
    }
}
