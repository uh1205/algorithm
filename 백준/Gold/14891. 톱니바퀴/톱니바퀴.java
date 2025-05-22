import java.io.*;
import java.util.*;

public class Main {

    static Deque<Integer>[] gears = new Deque[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 초기 상태 입력
        for (int i = 0; i < 4; i++) {
            gears[i] = new ArrayDeque<>();
            String line = br.readLine();
            for (char c : line.toCharArray()) {
                gears[i].addLast(c - '0');
            }
        }

        int k = Integer.parseInt(br.readLine());

        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearIdx = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            int[] rotateDir = new int[4];
            rotateDir[gearIdx] = direction;

            // 왼쪽 전파
            for (int i = gearIdx - 1; i >= 0; i--) {
                if (gears[i].toArray()[2].equals(gears[i + 1].toArray()[6])) break;
                rotateDir[i] = -rotateDir[i + 1];
            }

            // 오른쪽 전파
            for (int i = gearIdx + 1; i < 4; i++) {
                if (gears[i - 1].toArray()[2].equals(gears[i].toArray()[6])) break;
                rotateDir[i] = -rotateDir[i - 1];
            }

            // 회전 실행
            for (int i = 0; i < 4; i++) {
                if (rotateDir[i] == 1) rotateClockwise(gears[i]);
                else if (rotateDir[i] == -1) rotateCounterClockwise(gears[i]);
            }
        }

        // 점수 계산 (12시방향이 1인 톱니: 1, 2, 4, 8 점)
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i].peekFirst() == 1) score += (1 << i);
        }

        System.out.println(score);
    }

    static void rotateClockwise(Deque<Integer> dq) {
        dq.addFirst(dq.pollLast());
    }

    static void rotateCounterClockwise(Deque<Integer> dq) {
        dq.addLast(dq.pollFirst());
    }
}