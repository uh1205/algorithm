import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M;
    static int[][] map;
    static Map<Integer, Shark> sharks = new HashMap<>();
    static int[] dr = {0, -1, 1, 0, 0}; // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
    static int[] dc = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()); // 속력
            int d = Integer.parseInt(st.nextToken()); // 방향 (1:위, 2:아래, 3:오른쪽, 4:왼쪽)
            int z = Integer.parseInt(st.nextToken()); // 크기
            sharks.put(z, new Shark(r, c, s, d, z));
            map[r][c] = z; // 상어의 위치와 크기 기록
        }

        int result = 0;

        for (int col = 1; col <= C; col++) {
            // 1. 가장 가까운 상어 잡기
            Shark nearest = catchNearestShark(col);
            if (nearest != null) {
                result += nearest.z;
            }
            // 2. 상어 이동 (겹칠 경우 큰 상어가 작은 상어를 먹음)
            moveSharks();
        }

        System.out.println(result);
    }

    static Shark catchNearestShark(int col) {
        for (int r = 1; r <= R; r++) {
            if (map[r][col] != 0) return sharks.remove(map[r][col]);
        }
        return null;
    }

    static void moveSharks() {
        int[][] newMap = new int[R + 1][C + 1];
        List<Integer> toRemove = new ArrayList<>();

        for (Shark shark : sharks.values()) {
            int move = shark.s;
            while (move-- > 0) {
                // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
                if (shark.r == 1 && shark.d == 1) shark.d = 2;
                else if (shark.c == 1 && shark.d == 4) shark.d = 3;
                else if (shark.r == R && shark.d == 2) shark.d = 1;
                else if (shark.c == C && shark.d == 3) shark.d = 4;
                shark.r += dr[shark.d];
                shark.c += dc[shark.d];
            }
            int cur = newMap[shark.r][shark.c];
            if (cur == 0) {
                newMap[shark.r][shark.c] = shark.z;
                continue;
            }
            if (cur > shark.z) {
//                sharks.remove(shark.z);
                toRemove.add(shark.z);
            } else if (cur < shark.z) {
//                sharks.remove(cur);
                toRemove.add(cur);
                newMap[shark.r][shark.c] = shark.z;
            }
        }
        for (int k : toRemove) {
            sharks.remove(k);
        }
        map = newMap;
    }

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}