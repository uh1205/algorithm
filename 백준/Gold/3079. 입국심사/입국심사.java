import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long M;
    static long[] times;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        times = new long[N];
        long maxTime = 0;

        for (int i = 0; i < N; i++) {
            times[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, times[i]);
        }

        // 이분 탐색 범위: 최소 1 ~ 최대 maxTime * M
        long left = 1;
        long right = maxTime * M;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canProcessAll(mid)) {
                answer = mid;      // 가능한 시간 중 최소값을 찾아야 하므로 저장하고 왼쪽 탐색
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canProcessAll(long timeLimit) {
        long people = 0;
        for (int i = 0; i < N; i++) {
            people += timeLimit / times[i];
            if (people >= M) return true; // 조기 탈출 가능
        }
        return false;
    }
}