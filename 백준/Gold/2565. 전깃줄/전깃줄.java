import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Line[] lines = new Line[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines); // A 기준 정렬

        int[] dp = new int[N]; // LIS 저장용
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (lines[i].b > lines[j].b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLIS = 0;
        for (int val : dp) {
            maxLIS = Math.max(maxLIS, val);
        }

        System.out.println(N - maxLIS); // 전체 - LIS 길이 = 제거할 개수
    }

    static class Line implements Comparable<Line> {
        int a, b;

        Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line other) {
            return this.a - other.a; // A 기준으로 정렬
        }
    }
}