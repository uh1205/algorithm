import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 구간 합
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        int ans = sum;
        
        // 슬라이딩 윈도우 시작
        // i: 새로 들어오는 값의 인덱스
        for (int i = K; i < N; i++) {
            // 새로 들어오는 값을 더하고, 윈도우에서 빠지는 값을 뺌
            sum = sum + arr[i] - arr[i - K];
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}
