import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] arr, num;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        num = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0, 0);
        System.out.println(sb);
    }

    static void dfs(int depth, int start) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(num[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        int lastUsed = -1; // 추가
        for (int i = start; i < N; i++) {
            if (arr[i] == lastUsed) continue;  // 추가
            num[depth] = arr[i];
            lastUsed = arr[i];  // 추가
            dfs(depth + 1, i + 1);
        }
    }
}