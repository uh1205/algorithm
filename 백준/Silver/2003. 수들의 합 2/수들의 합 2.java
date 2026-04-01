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

        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] pre = new int[N + 1];
        pre[1] = arr[1];

        for (int i = 2; i <= N; i++) {
            pre[i] = pre[i - 1] + arr[i];
        }

        // i ~ j 구간합 = pre[j] - pre[i - 1]
        int ans = 0;

        int left = 1;
        int right = 1;

        while (left <= N && right <= N) {
            int sum = pre[right] - pre[left - 1];

            if (sum < M) {
                right++;
            } else if (sum > M) {
                left++;
            } else {
                ans++;
                left++;
                right++;
            }

            if (left > right) {
                right = left;
            }
        }

        System.out.println(ans);
    }
}
