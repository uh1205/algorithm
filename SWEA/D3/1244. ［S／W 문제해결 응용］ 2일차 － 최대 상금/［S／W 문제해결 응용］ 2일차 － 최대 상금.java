import java.io.*;
import java.util.*;

class Solution {
    static String max;
    static int K;
    static Set<String> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String number = st.nextToken();
            K = Integer.parseInt(st.nextToken());

            max = "0";
            visited = new HashSet<>();

            dfs(number.toCharArray(), 0);

            sb.append('#').append(tc).append(' ').append(max).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(char[] arr, int depth) {
        if (depth == K) {
            String result = new String(arr);
            if (max.compareTo(result) < 0) {
                max = result;
            }
            return;
        }

        String key = new String(arr) + "," + depth;
        if (visited.contains(key)) return;
        visited.add(key);

        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                swap(arr, i, j);
                dfs(arr, depth + 1);
                swap(arr, i, j);
            }
        }
    }

    static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}