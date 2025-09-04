import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] add;        // 겨울에 추가되는 양분 A
    static int[][] soil;       // 현재 양분
    @SuppressWarnings("unchecked")
    static Deque<Integer>[][] trees; // 각 칸의 나이 오름차순 덱
    static final int[] dr = {-1,-1,-1,0,0,1,1,1};
    static final int[] dc = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        add = new int[N][N];
        soil = new int[N][N];
        trees = new ArrayDeque[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                add[r][c] = Integer.parseInt(st.nextToken());
                soil[r][c] = 5;              // 초기 양분 5
                trees[r][c] = new ArrayDeque<>();
            }
        }

        // 초기 나무 입력: 같은 칸에 여러 그루 가능 -> 덱에 삽입 후 정렬 유지 필요
        // 입력에서 나이 정렬이 보장되지 않으므로, 우선 리스트에 모아 정렬 후 덱으로 옮긴다.
        List<int[]> temp = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            temp.add(new int[]{x, y, z});
        }
        // 같은 칸끼리 모아 나이 기준 정렬 후 덱 구성
        temp.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        int idx = 0;
        while (idx < temp.size()) {
            int r = temp.get(idx)[0];
            int c = temp.get(idx)[1];
            while (idx < temp.size() && temp.get(idx)[0] == r && temp.get(idx)[1] == c) {
                trees[r][c].addLast(temp.get(idx)[2]); // 오름차순으로 들어감
                idx++;
            }
        }

        // K년 시뮬레이션
        for (int year = 0; year < K; year++) {
            // 봄 + 여름
            int[][] breed = new int[N][N]; // 가을 번식 개수 기록
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (trees[r][c].isEmpty()) continue;

                    Deque<Integer> cur = trees[r][c];
                    Deque<Integer> next = new ArrayDeque<>();
                    int deadNutrient = 0;

                    // 어린 나무부터(앞에서부터)
                    int size = cur.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        int age = cur.pollFirst();
                        if (soil[r][c] >= age) {
                            soil[r][c] -= age;
                            int newAge = age + 1;
                            next.addLast(newAge);
                            if (newAge % 5 == 0) breed[r][c]++; // 가을 번식 후보
                        } else {
                            deadNutrient += age / 2; // 여름에 양분으로 변함
                        }
                    }
                    soil[r][c] += deadNutrient;
                    trees[r][c] = next; // 살아남은 나무들로 갱신
                }
            }

            // 가을: 번식
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int times = breed[r][c];
                    if (times == 0) continue;
                    for (int d = 0; d < 8; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                        // 나이 1을 times개 앞쪽에 삽입(오름차순 유지)
                        // 동일 나이(1)끼리는 순서 영향 없음
                        for (int t = 0; t < times; t++) {
                            trees[nr][nc].addFirst(1);
                        }
                    }
                }
            }

            // 겨울: 양분 추가
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    soil[r][c] += add[r][c];
                }
            }
        }

        // 살아있는 나무 수 합산
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += trees[r][c].size();
            }
        }
        System.out.println(answer);
    }
}