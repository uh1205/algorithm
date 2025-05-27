import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> dp = new ArrayList<>();
        int[] index = new int[N];

        for (int i = 0; i < N; i++) {
            int idx = Collections.binarySearch(dp, A[i]);
            if (idx < 0) idx = -idx - 1;

            if (idx == dp.size()) dp.add(A[i]);
            else dp.set(idx, A[i]);

            index[i] = idx;
        }

        int len = dp.size(); // LIS 길이
        System.out.println(len);

        // LIS 역추적
        int[] LIS = new int[len];
        int cur = len - 1;

        for (int i = N - 1; i >= 0; i--) {
            if (index[i] == cur) {
                LIS[cur] = A[i];
                cur--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : LIS) {
            sb.append(i).append(' ');
        }

        System.out.println(sb.toString().trim());
    }
}