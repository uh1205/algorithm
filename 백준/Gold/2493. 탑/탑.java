import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Tower> stack = new Stack<>();
        int[] result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());

            // 현재 탑보다 낮은 탑은 레이저를 수신할 수 없음 → pop
            while (!stack.isEmpty() && stack.peek().height < height) {
                stack.pop();
            }

            // 스택이 비지 않았다면 수신 가능한 탑이 존재
            if (!stack.isEmpty()) {
                result[i] = stack.peek().index;
            } else {
                result[i] = 0;
            }

            // 현재 탑을 스택에 push
            stack.push(new Tower(i + 1, height)); // 인덱스는 1-based
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    static class Tower {
        int index, height;

        public Tower(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}