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

        long left = 1;
        long right = maxTime * M;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canProcessAll(mid)) {
                answer = mid;
                right = mid - 1; // 더 빠른 시간 탐색
            } else {
                left = mid + 1; // 더 많은 시간 필요
            }
        }

        System.out.println(answer);
    }

    // mid 시간 안에 M명 이상 심사 가능한지 판단
    static boolean canProcessAll(long mid) {
        long people = 0;
        for (int i = 0; i < N; i++) {
            people += mid / times[i];
            if (people >= M) return true; // 조기 종료
        }
        return false;
    }
}