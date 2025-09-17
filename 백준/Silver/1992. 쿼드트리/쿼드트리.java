import java.io.*;

public class Main {
    static char[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        compress(0, 0, N);

        System.out.println(sb);
    }

    static void compress(int r, int c, int size) {
        // 모두 같은 값이면 그대로 출력
        if (isAllSame(r, c, size)) {
            sb.append(map[r][c]);
            return;
        }

        sb.append('(');
        int half = size / 2;

        // 1. 좌상
        compress(r, c, half);
        // 2. 우상
        compress(r, c + half, half);
        // 3. 좌하
        compress(r + half, c, half);
        // 4. 우하
        compress(r + half, c + half, half);

        sb.append(')');
    }

    // 해당 영역이 모두 같은 값인지 검사
    static boolean isAllSame(int r, int c, int size) {
        char first = map[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != first) return false;
            }
        }
        return true;
    }
}