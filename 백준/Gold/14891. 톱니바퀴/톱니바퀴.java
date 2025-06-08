import java.io.*;
import java.util.*;

public class Main {
    static char[][] gears = new char[5][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 4; i++) {
            gears[i] = br.readLine().toCharArray();
        }

        int K = Integer.parseInt(br.readLine()); // 회전 횟수

        for (int i = 0; i < K; i++) {
            char[][] copiedGears = copyGears();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken()); // 1이면 시계, -1이면 반시계 방향
            rotateGear(copiedGears[gearNum], direction);

            int cur = gearNum;
            int curDir = direction;
            int left = gearNum - 1;
            while (left >= 1) {
                if (gears[cur][6] != gears[left][2]) {
                    rotateGear(copiedGears[left], -curDir);
                    cur = left;
                    curDir = -curDir;
                    left--;
                } else {
                    break;
                }
            }

            cur = gearNum;
            curDir = direction;
            int right = gearNum + 1;
            while (right <= 4) {
                if (gears[cur][2] != gears[right][6]) {
                    rotateGear(copiedGears[right], -curDir);
                    cur = right;
                    curDir = -curDir;
                    right++;
                } else {
                    break;
                }
            }

            gears = copiedGears;
        }

        int total = 0;
        for (int i = 1; i <= 4; i++) {
            if (gears[i][0] == '1') {
                total += (int) Math.pow(2, i - 1);
            }
        }

        System.out.println(total);
    }

    static char[][] copyGears() {
        char[][] copied = new char[5][8];
        for (int i = 0; i < copied.length; i++) {
            copied[i] = gears[i].clone();
        }
        return copied;
    }

    static void rotateGear(char[] arr, int direction) {
        if (direction == 1) rotateRight(arr);
        else rotateLeft(arr);
    }

    // 시계 방향 회전: 오른쪽으로 한 칸 이동
    static void rotateRight(char[] arr) {
        char last = arr[7];
        for (int i = 7; i >= 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
    }

    // 반시계 방향 회전: 왼쪽으로 한 칸 이동
    static void rotateLeft(char[] arr) {
        char first = arr[0];
        for (int i = 0; i <= 6; i++) {
            arr[i] = arr[i + 1];
        }
        arr[7] = first;
    }
}
