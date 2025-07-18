import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Deque<Pair> dq = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 덱 뒤에서 값 삭제: A[i]보다 큰 값 제거
            while (!dq.isEmpty() && dq.peekLast().value > num) {
                dq.pollLast();
            }

            dq.offerLast(new Pair(num, i));

            // 덱 앞에서 범위 벗어난 인덱스 제거
            if (dq.peekFirst().idx <= i - L) {
                dq.pollFirst();
            }

            sb.append(dq.peekFirst().value).append(' ');
        }

        System.out.print(sb);
    }

    static class Pair {
        int value, idx;
        Pair(int v, int i) { value = v; idx = i; }
    }
}