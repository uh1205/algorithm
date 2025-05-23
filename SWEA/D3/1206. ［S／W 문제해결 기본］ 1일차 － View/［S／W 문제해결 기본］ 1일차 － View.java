import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int T = 1; T <= 10; T++) {
            int N = Integer.parseInt(br.readLine());

            int[] height = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            int result = 0;
            for (int i = 2; i <= N - 3; i++) {
                int leftMax = Math.max(height[i - 1], height[i - 2]);
                int rightMax = Math.max(height[i + 1], height[i + 2]);
                int sideMax = Math.max(leftMax, rightMax);

                // 현재 건물에 조망권이 있는 경우
                if (height[i] > sideMax) {
                    result += height[i] - sideMax;
                }
            }

            sb.append('#').append(T).append(' ').append(result).append('\n');
        }

        System.out.println(sb);
    }
}