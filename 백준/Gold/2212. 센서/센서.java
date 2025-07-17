import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(solution(arr));
    }

    static int solution(int[] arr) {
        if (N == 1 || N <= K) {
            return 0;
        }

        if (K == 1) {
            return arr[N - 1] - arr[0];
        }

        int[] dist = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = arr[i + 1] - arr[i];
        }

        Arrays.sort(dist);

        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += dist[i];
        }

        return sum;
    }
}