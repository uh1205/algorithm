import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[K];
        long high = 0;

        for (int i = 0; i < K; i++) {
            arr[i] = Long.parseLong(br.readLine());
            high = Math.max(high, arr[i]);
        }

        // 랜선 길이는 1부터 시작, 최대값은 high
        long low = 1;
        long ans = 0;

        while (low <= high) {
            long mid = (low + high) >>> 1;
            long count = 0;

            for (long a : arr) {
                count += (a / mid);
            }

            if (count >= N) {
                // N개 이상을 만들 수 있다면, 일단 정답 후보로 저장하고
                // 더 길게 자를 수 있는지 확인 (오른쪽 탐색)
                ans = mid;
                low = mid + 1;
            } else {
                // N개를 못 만든다면, 너무 길게 잡은 것이므로 길이를 줄임
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
