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

        int[] arr = new int[K];

        long low = 1;
        long high = 0;

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            high = Math.max(high, arr[i]);
        }

        // 랜선의 길이를 mid로 설정했을 때, N개 이상 만들 수 있는가?
        long ans = 0;

        while (low <= high) {
            long mid = (low + high) >>> 1;

            long count = 0;

            for (int a : arr) {
                count += a / mid;
            }

            if (count >= N) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
