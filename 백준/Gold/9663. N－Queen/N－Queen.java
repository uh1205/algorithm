import java.io.*;

class Main {

    static int N, cnt = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        dfs(0);
        System.out.println(cnt);
    }

    static void dfs(int r) {
        // 모든 퀸 배치 완료 시 카운트 증가
        if (r == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isValid(r, i)) { // 유효한 위치인지 확인
                arr[r] = i; // 퀸 배치
                dfs(r + 1); // 다음 row로 이동
            }
        }
    }

    static boolean isValid(int r, int c) {
        for (int i = 0; i < r; i++) { // 이전 row들만 확인
            // 같은 column에 위치
            if (arr[i] == c) return false;
            // 같은 대각선(좌, 우)에 위치
            if (Math.abs(arr[i] - c) == r - i) return false;
        }
        return true;
    }
}