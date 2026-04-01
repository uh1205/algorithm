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
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (true) {
            if (sum >= M) {
                sum -= arr[left++];
            } else if (right == N) {
                // 끝에 도달했는데 합이 M보다 작다면 더 이상 구할 수 없음
                break;
            } else {
                sum += arr[right++];
            }

            if (sum == M) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
