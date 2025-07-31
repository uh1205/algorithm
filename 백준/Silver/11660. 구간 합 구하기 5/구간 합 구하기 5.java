import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] sum = new int[N + 1][N + 1]; // (1,1) ~ (r,c) 사각형 합

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                sum[r][c] = sum[r - 1][c] 
                        + sum[r][c - 1]
                        - sum[r - 1][c - 1]
                        + map[r][c];
            }
        }

        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int result = sum[r2][c2] 
                    - sum[r2][c1 - 1]
                    - sum[r1 - 1][c2] 
                    + sum[r1 - 1][c1 - 1];
            
            sb.append(result).append('\n');
        }

        System.out.println(sb);
    }
}