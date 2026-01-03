import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] durability;
    static boolean[] hasRobot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        durability = new int[2 * N];
        hasRobot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            durability[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;
        while (true) {
            step++;

            // 1. 벨트와 로봇 회전
            rotate();

            // 2. 로봇 이동
            moveRobots();

            // 3. 로봇 올리기
            if (durability[0] > 0) {
                hasRobot[0] = true;
                durability[0]--;
            }

            // 4. 종료 조건 확인
            if (countZeros() >= K) {
                System.out.println(step);
                break;
            }
        }
    }

    private static void rotate() {
        // 내구도 배열 회전
        int temp = durability[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            durability[i] = durability[i - 1];
        }
        durability[0] = temp;

        // 로봇 배열 회전
        for (int i = N - 1; i > 0; i--) {
            hasRobot[i] = hasRobot[i - 1];
        }
        hasRobot[0] = false; // 새로 들어온 칸은 비어있음
        hasRobot[N - 1] = false; // 내리는 위치 도달 시 즉시 내림
    }

    private static void moveRobots() {
        // 가장 먼저 올라간 로봇부터 (내리는 위치와 가까운 쪽부터)
        for (int i = N - 2; i >= 0; i--) {
            if (hasRobot[i]) {
                // 다음 칸에 로봇이 없고 내구도가 1 이상이면
                if (!hasRobot[i + 1] && durability[i + 1] > 0) {
                    hasRobot[i] = false;
                    hasRobot[i + 1] = true;
                    durability[i + 1]--;
                }
            }
        }
        hasRobot[N - 1] = false; // 내리는 위치 도달 시 즉시 내림
    }

    private static int countZeros() {
        int count = 0;
        for (int val : durability) {
            if (val == 0) count++;
        }
        return count;
    }
}