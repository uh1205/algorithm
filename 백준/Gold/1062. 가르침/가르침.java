import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] words;
    static boolean[] visited;
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new int[N];
        visited = new boolean[26];

        // 필수 알파벳: a, n, t, i, c
        if (K < 5) {
            System.out.println(0);
            return;
        }
        if (K == 26) {
            System.out.println(N);
            return;
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = 0;
            for (char ch : word.toCharArray()) {
                words[i] |= (1 << (ch - 'a'));
            }
        }

        dfs(0, 0);
        System.out.println(maxCount);
    }

    // depth: 현재 선택한 알파벳 수, start: 다음에 선택할 알파벳 인덱스
    static void dfs(int depth, int start) {
        if (depth == K - 5) {
            int count = 0;
            int learnedMask = 0;
            for (int i = 0; i < 26; i++) {
                if (visited[i]) {
                    learnedMask |= (1 << i);
                }
            }
            for (int w : words) {
                if ((w & learnedMask) == w) count++;
            }
            maxCount = Math.max(maxCount, count);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}