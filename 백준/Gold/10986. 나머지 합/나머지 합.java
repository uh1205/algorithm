import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] count = new long[M]; // 나머지 카운트
        long sum = 0;
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            int remainder = (int) (sum % M);
            if (remainder == 0) answer++; // prefixSum[0 ~ i] % M == 0
            count[remainder]++;
        }

        for (int i = 0; i < M; i++) {
            if (count[i] > 1) {
                answer += count[i] * (count[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}