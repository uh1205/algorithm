import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] num;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[M];
        dfs(0, 1);
        System.out.println(sb);
    }

    static void dfs(int depth, int start) {
        if (depth == M) {
            for (int n : num) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) { // 1 ~ N 중 M개 뽑기
            num[depth] = i;
            dfs(depth + 1, i);
        }
    }
}