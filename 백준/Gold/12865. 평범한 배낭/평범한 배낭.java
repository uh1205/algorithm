import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 수
        int maxWeight = Integer.parseInt(st.nextToken()); // 최대 무게

        int[] dp = new int[maxWeight + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken()); // 물건 무게
            int value = Integer.parseInt(st.nextToken());  // 물건 가치

            // 뒤에서부터 순회해야 같은 물건을 여러 번 담지 않음
            for (int j = maxWeight; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[maxWeight]);
    }
}