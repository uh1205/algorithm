import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] S = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
        }

        long ans = 0;

        // S[i] - K가 이전에 몇번 등장?
        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);

        for (int i = 1; i <= N; i++) {
            long cur = S[i] - K;
            if (map.containsKey(cur)) {
                ans += map.get(cur);
            }
            map.put(S[i], map.getOrDefault(S[i], 0L) + 1);
        }

        System.out.println(ans);
    }
}
