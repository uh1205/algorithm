import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 이동 횟수 = 2^n - 1
        sb.append((1 << N) - 1).append('\n');

        hanoi(N, 1, 3, 2);

        System.out.println(sb);
    }

    static void hanoi(int n, int from, int to, int via) {
        if (n == 1) {
            sb.append(from).append(' ').append(to).append('\n');
            return;
        }
        // 1. n-1개를 from -> via
        hanoi(n - 1, from, via, to);

        // 2. 가장 큰 원판을 from -> to
        sb.append(from).append(' ').append(to).append('\n');

        // 3. n-1개를 via -> to
        hanoi(n - 1, via, to, from);
    }
}