import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] down = new int[H + 2]; // 석순
        int[] up = new int[H + 2];   // 종유석

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (i % 2 == 0) { // 석순
                down[x]++;
            } else { // 종유석
                up[x]++;
            }
        }

        // 누적합 구하기
        int[] downSum = new int[H + 2];
        int[] upSum = new int[H + 2];

        for (int h = H; h >= 1; h--) {
            downSum[h] = downSum[h + 1] + down[h];
            upSum[h] = upSum[h + 1] + up[h];
        }

        int minBreak = Integer.MAX_VALUE;
        int count = 0;

        for (int h = 1; h <= H; h++) {
            int breakCnt = downSum[h] + upSum[H - h + 1];
            if (breakCnt < minBreak) {
                minBreak = breakCnt;
                count = 1;
            } else if (breakCnt == minBreak) {
                count++;
            }
        }

        System.out.println(minBreak + " " + count);
    }
}