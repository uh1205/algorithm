import java.io.*;
import java.util.*;

public class Main {

    static int N, M, minSum = Integer.MAX_VALUE;
    static int[][] map;
    static List<int[]> chicken;
    static int[][] survived;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        chicken = new ArrayList<>();
        survived = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        choose(0, 0);
        System.out.println(minSum);
    }


    static void choose(int depth, int start) {
        // 살아남을 치킨집들을 다 골랐으면
        if (depth == M) {
            calc(); // 도시의 치킨거리 계산
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            survived[depth] = chicken.get(i);
            choose(depth + 1, i + 1);
        }
    }

    static void calc() {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    // 해당 집과 치킨집들 중에서 최소 거리 구하기
                    int minD = Integer.MAX_VALUE;
                    for (int[] sur : survived) {
                        int d = Math.abs(sur[0] - i) + Math.abs(sur[1] - j);
                        minD = Math.min(minD, d);
                    }
                    // 도시의 치킨 거리
                    sum += minD;
                }
            }
        }
        // 도시의 치킨 거리 중 최소로 갱신
        minSum = Math.min(minSum, sum);
    }
}