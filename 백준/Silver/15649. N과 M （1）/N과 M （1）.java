import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static boolean[] visited;
    static int[] num;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        num = new int[M];
        perm(0);
        System.out.println(sb);
    }

    static void perm(int depth) {
        if (depth == M) {
            for (int n : num) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) { // 1 ~ N 중 M개 뽑기
            if (visited[i]) continue;
            visited[i] = true;
            num[depth] = i;
            perm(depth + 1);
            visited[i] = false;
        }
    }
}