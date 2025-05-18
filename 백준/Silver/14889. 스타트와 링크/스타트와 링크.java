import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] S;
    static boolean[] selected;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        selected = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0번 사람은 항상 스타트 팀에 포함시키는 방식으로 중복 제거
        selected[0] = true;
        divideTeams(1, 1);

        System.out.println(minDiff);
    }

    static void divideTeams(int idx, int count) {
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        for (int i = idx; i < N; i++) {
            selected[i] = true;
            divideTeams(i + 1, count + 1);
            selected[i] = false;
        }
    }

    static void calculateDifference() {
        int startSum = 0, linkSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (selected[i] && selected[j]) {
                    startSum += S[i][j] + S[j][i];
                } else if (!selected[i] && !selected[j]) {
                    linkSum += S[i][j] + S[j][i];
                }
            }
        }

        int diff = Math.abs(startSum - linkSum);
        minDiff = Math.min(minDiff, diff);
    }
}