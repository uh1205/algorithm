import java.io.*;
import java.util.*;

class Main {
    static int N, M, r, c, K;
    static int[] dice = new int[7];
    static int[][] map;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            boolean isRolled = false;
            int cmd = Integer.parseInt(st.nextToken());
            if (cmd == 1) isRolled = rollDiceEast();
            if (cmd == 2) isRolled = rollDiceWest();
            if (cmd == 3) isRolled = rollDiceNorth();
            if (cmd == 4) isRolled = rollDiceSouth();

            if (isRolled) {
                if (map[r][c] == 0) {
                    map[r][c] = dice[6]; // 바닥면
                } else {
                    dice[6] = map[r][c];
                    map[r][c] = 0;
                }
                result.append(dice[1]).append('\n'); // 윗면 출력
            }
        }

        System.out.println(result);
    }

    static boolean rollDiceEast() {
        if (c + 1 >= M) return false;
        c++;
        int temp = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = temp;
        return true;
    }

    static boolean rollDiceWest() {
        if (c - 1 < 0) return false;
        c--;
        int temp = dice[1];
        dice[1] = dice[3];
        dice[3] = dice[6];
        dice[6] = dice[4];
        dice[4] = temp;
        return true;
    }

    static boolean rollDiceNorth() {
        if (r - 1 < 0) return false;
        r--;
        int temp = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = temp;
        return true;
    }

    static boolean rollDiceSouth() {
        if (r + 1 >= N) return false;
        r++;
        int temp = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = temp;
        return true;
    }

}