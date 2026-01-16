import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Map<Integer, Set<Integer>> favorites = new LinkedHashMap<>(); // 입력 순서 유지를 위해

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int totalStudents = N * N;
        int[] order = new int[totalStudents];

        for (int i = 0; i < totalStudents; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            order[i] = student;
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            favorites.put(student, set);
        }

        // 1. 순서대로 학생 자리 배치
        for (int student : order) {
            placeStudent(student);
        }

        // 2. 만족도 계산
        System.out.println(calculateSatisfaction());
    }

    static void placeStudent(int student) {
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        Set<Integer> myFavorites = favorites.get(student);

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != 0) {
                    continue; // 이미 주인이 있는 자리
                }

                int likeCount = 0;
                int emptyCount = 0;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (map[nr][nc] == 0) {
                            emptyCount++;
                        } else if (myFavorites.contains(map[nr][nc])) {
                            likeCount++;
                        }
                    }
                }
                pq.add(new Cell(r, c, likeCount, emptyCount));
            }
        }

        Cell best = pq.poll();
        map[best.r][best.c] = student;
    }

    static int calculateSatisfaction() {
        int totalScore = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int student = map[r][c];
                Set<Integer> myFavorites = favorites.get(student);
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (myFavorites.contains(map[nr][nc])) {
                            count++;
                        }
                    }
                }

                if (count > 0) {
                    totalScore += Math.pow(10, count - 1);
                }
            }
        }
        return totalScore;
    }

    // 각 칸의 상태를 비교하기 위한 보조 클래스
    static class Cell implements Comparable<Cell> {
        int r, c, likeCount, emptyCount;

        Cell(int r, int c, int likeCount, int emptyCount) {
            this.r = r;
            this.c = c;
            this.likeCount = likeCount;
            this.emptyCount = emptyCount;
        }

        @Override
        public int compareTo(Cell o) {
            if (this.likeCount != o.likeCount) {
                return o.likeCount - this.likeCount; // 1. 좋아하는 학생 많은 순
            }
            if (this.emptyCount != o.emptyCount) {
                return o.emptyCount - this.emptyCount; // 2. 빈칸 많은 순
            }
            if (this.r != o.r) {
                return this.r - o.r; // 3. 행 낮은 순
            }
            return this.c - o.c; // 4. 열 낮은 순
        }
    }
}
