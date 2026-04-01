import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        // map: <누적합의 값, 해당 값이 나온 횟수>
        Map<Long, Long> map = new HashMap<>();
        
        // 초기값: 누적합이 0인 경우가 한 번 있다고 설정 (처음부터 현재까지의 합이 K인 경우 대비)
        map.put(0L, 1L);

        long currentSum = 0;
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            currentSum += Long.parseLong(st.nextToken());

            // 우리가 찾는 조건: currentSum - 이전누적합 = K
            // 즉, 이전누적합 = currentSum - K
            if (map.containsKey(currentSum - K)) {
                ans += map.get(currentSum - K);
            }

            // 현재 누적합을 맵에 업데이트
            map.put(currentSum, map.getOrDefault(currentSum, 0L) + 1);
        }

        System.out.println(ans);
    }
}