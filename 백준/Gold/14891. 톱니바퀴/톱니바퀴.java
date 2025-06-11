import java.io.*;
import java.util.*;

public class Main {
    static char[][] gears = new char[4][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            gears[i] = br.readLine().toCharArray();
        }

        int K = Integer.parseInt(br.readLine());

        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearIdx = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            int[] directions = new int[4];
            directions[gearIdx] = direction;

            for (int left = gearIdx - 1; left >= 0; left--) {
                if (gears[left][2] != gears[left + 1][6]) {
                    directions[left] = -directions[left + 1];
                } else {
                    break;
                }
            }

            for (int right = gearIdx + 1; right <= 3; right++) {
                if (gears[right - 1][2] != gears[right][6]) {
                    directions[right] = -directions[right - 1];
                } else {
                    break;
                }
            }

            for (int i = 0; i < 4; i++) {
                if (directions[i] == 1) rotateRight(i);
                else if (directions[i] == -1) rotateLeft(i);
            }
        }

        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == '1') {
                score += (1 << i);
            }
        }

        System.out.println(score);
    }

    static void rotateRight(int gearIdx) {
        char last = gears[gearIdx][7];
        for (int i = 7; i >= 1; i--) {
            gears[gearIdx][i] = gears[gearIdx][i - 1];
        }
        gears[gearIdx][0] = last;
    }

    static void rotateLeft(int gearIdx) {
        char first = gears[gearIdx][0];
        for (int i = 0; i <= 6; i++) {
            gears[gearIdx][i] = gears[gearIdx][i + 1];
        }
        gears[gearIdx][7] = first;
    }
}