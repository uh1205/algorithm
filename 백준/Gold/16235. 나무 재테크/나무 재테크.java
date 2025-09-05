import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] add;
    static int[][] soil;
    static Deque<Integer>[][] trees;
    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
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
                soil[r][c] = 5;
                trees[r][c] = new ArrayDeque<>();
            }
        }

        // 초기 나무 입력: 우선 리스트에 담아 정렬 후 덱으로 옮기기
        List<int[]> temp = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            temp.add(new int[]{x, y, z});
        }

        temp.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // 행 기준 오름차순
            if (a[1] != b[1]) return a[1] - b[1]; // 같은 행이면 열 기준 오름차순
            return a[2] - b[2]; // 같은 행, 같은 열이면 나이 기준 오름차순
        });

        int idx = 0;
        while (idx < temp.size()) {
            int r = temp.get(idx)[0];
            int c = temp.get(idx)[1];
            // (r, c) 위치에 있는 나무들을 모두 덱에 넣기
            while (idx < temp.size() && temp.get(idx)[0] == r && temp.get(idx)[1] == c) {
                trees[r][c].addLast(temp.get(idx)[2]); // 오름차순으로 들어감
                idx++;
            }
        }

        // K년 시뮬레이션
        for (int year = 0; year < K; year++) {
            // 봄 + 여름
            int[][] breed = new int[N][N]; // 가을 번식 대상 개수
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (trees[r][c].isEmpty()) continue;

                    // 현재 위치 (r, c)의 나무들
                    Deque<Integer> cur = trees[r][c];
                    Deque<Integer> next = new ArrayDeque<>();
                    int deadNutrient = 0; // 죽어서 생긴 양분

                    int size = cur.size();
                    for (int i = 0; i < size; i++) {
                        int age = cur.pollFirst(); // 가장 어린 나무
                        if (soil[r][c] >= age) {
                            soil[r][c] -= age; // 양분 감소
                            int newAge = age + 1; // 나이 증가
                            next.addLast(newAge); // 맨 뒤에 저장
                            if (newAge % 5 == 0) breed[r][c]++; // 가을 번식 대상
                        } else {
                            // 양분을 먹지 못해 죽고, 여름에 양분으로 변함
                            deadNutrient += age / 2;
                        }
                    }

                    soil[r][c] += deadNutrient; // 양분 증가
                    trees[r][c] = next; // 살아남은 나무들로 갱신
                }
            }

            // 가을: 번식
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int times = breed[r][c]; // 현재 위치의 번식 대상 개수
                    if (times == 0) continue;
                    for (int d = 0; d < 8; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                        // 나이 1을 times개 맨앞에 삽입(오름차순 유지)
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