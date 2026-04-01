import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int low = 0; // 강의 중 최댓값
        int high = 0; // 강의 총합

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            low = Math.max(low, arr[i]);
            high += arr[i];
        }

        // 블루레이 크기를 mid로 설정했을 때, 필요한 블루레이 개수가 M개 이하인가?
        int ans = high;

        while (low <= high) {
            int mid = (low + high) >>> 1;

            int count = 1;
            long sum = 0;

            for (int a : arr) {
                if (sum + a > mid) {
                    count++;
                    sum = a;
                } else {
                    sum += a;
                }
            }

            if (count <= M) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
