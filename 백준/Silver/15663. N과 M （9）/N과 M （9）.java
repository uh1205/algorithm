import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] arr;
    static boolean[] visited;
    static int[] num;
    static List<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        num = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (depth == M) {
            StringBuilder tmp = new StringBuilder();
            for (int n : num) {
                tmp.append(n).append(" ");
            }
            String s = String.valueOf(tmp);
            if (!list.contains(s)) {
                list.add(s);
                sb.append(tmp).append("\n");
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            num[depth] = arr[i];
            dfs(depth + 1);
            visited[i] = false;
        }
    }
}