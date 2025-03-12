import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 오름차순 정렬 (N log N)

        int L = 0, R = N - 1;
        int minDiff = Integer.MAX_VALUE;
        int ansL = 0, ansR = 0;

        while (L < R) { // 투 포인터 탐색 (N)
            int sum = arr[L] + arr[R];

            // 0에 더 가까운 값이면 갱신
            if (Math.abs(sum) < minDiff) {
                minDiff = Math.abs(sum);
                ansL = arr[L];
                ansR = arr[R];

                if (sum == 0) break; // 0이면 더 이상 탐색 불필요
            }

            if (sum < 0) L++; // 합이 음수면 L 증가 (더 큰 값 탐색)
            else R--; // 합이 양수면 R 감소 (더 작은 값 탐색)
        }

        System.out.println(ansL + " " + ansR);
    }
}