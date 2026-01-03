import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] durability;
    static boolean[] hasRobot;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        durability = new int[2 * N + 1];
        hasRobot = new boolean[2 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        start = 1;
        end = N;

        int answer = 0;
        while (true) {
            answer++;

            // 1. 벨트와 로봇 회전
            rotate();

            // 2. 로봇 이동
            moveRobots();

            // 3. 로봇 올리기
            if (durability[start] > 0) {
                hasRobot[start] = true;
                durability[start]--;
            }

            // 4. 내구도가 0인 칸의 개수가 K개 이상이면 종료
            if (countZero() >= K) {
                break;
            }
        }

        System.out.println(answer);
    }

    static void rotate() {
        start = getIndex(start - 1);
        end = getIndex(end - 1);

        if (hasRobot[end]) {
            hasRobot[end] = false;
        }
    }

    static int getIndex(int idx) {
        if (idx < 1) {
            return 2 * N + idx;
        }
        if (idx > 2 * N) {
            return idx - 2 * N;
        }
        return idx;
    }

    static void moveRobots() {
        for (int i = 1; i <= N - 1; i++) {
            moveRobotIfPossible(end - i);
        }
    }


    static void moveRobotIfPossible(int i) {
        int cur = getIndex(i);
        int next = getIndex(cur + 1);

        // 이동하려는 칸에 로봇이 있거나 내구도가 1 미만이면 이동 불가
        if (!hasRobot[cur] || hasRobot[next] || durability[next] < 1) {
            return;
        }

        hasRobot[cur] = false;
        hasRobot[next] = true;
        durability[next]--;

        if (hasRobot[end]) {
            hasRobot[end] = false;
        }
    }

    private static int countZero() {
        int count = 0;
        for (int i = 1; i <= 2 * N; i++) {
            if (durability[i] == 0) {
                count++;
            }
        }
        return count;
    }
}
