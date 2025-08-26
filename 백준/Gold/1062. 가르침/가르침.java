import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] words;
    static final char[] necessary = {'a', 'n', 't', 'i', 'c'};
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 필수 알파벳(a, n, t, i, c)을 못 배우는 경우
        if (K < 5) {
            System.out.println(0);
            return;
        }
        // 모든 알파벳을 배우는 경우
        if (K == 26) {
            System.out.println(N);
            return;
        }

        words = new int[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (char ch : word.toCharArray()) {
                words[i] |= (1 << (ch - 'a'));
            }
        }

        int selected = 0;
        for (char ch : necessary) {
            selected |= (1 << (ch - 'a'));
        }

        dfs(0, 0, selected);
        System.out.println(maxCount);
    }

    static void dfs(int depth, int start, int selected) {
        if (depth == K - 5) {
            int count = 0;
            for (int word : words) {
                if ((selected & word) == word) count++;
            }
            maxCount = Math.max(maxCount, count);
            return;
        }
        for (int i = start; i < 26; i++) {
            if ((selected & (1 << i)) != 0) continue;
            dfs(depth + 1, i + 1, selected | (1 << i));
        }
    }
}