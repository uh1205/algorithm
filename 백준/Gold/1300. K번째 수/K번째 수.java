import java.io.*;

public class Main {
    static int N, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = (long) N * N;
        long answer = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (countLessOrEqual(mid) >= k) {
                answer = mid;
                right = mid - 1; // 더 작은 값도 가능한지 탐색
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    // A[i][j] <= x인 수의 개수 세기
    static long countLessOrEqual(long x) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, x / i);
        }
        return count;
    }
}