import java.io.*;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        draw(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (char[] chars : map) {
            sb.append(chars).append('\n');
        }

        System.out.println(sb);
    }

    // r, c : 시작 좌표
    // size : 현재 정사각형 크기 (한 변의 길이)
    // blank : 공백만 채워야 하는지 여부
    static void draw(int r, int c, int size, boolean blank) {
        if (blank) {
            // 현재 정사각형을 공백으로 채우기
            for (int i = r; i < r + size; i++) {
                for (int j = c; j < c + size; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            map[r][c] = '*';
            return;
        }

        int newSize = size / 3; // 예: 27 -> 9
        int cnt = 0;
        for (int i = r; i < r + size; i += newSize) {
            for (int j = c; j < c + size; j += newSize) {
                cnt++;
                if (cnt == 5) { // 가운데 블록인 경우
                    draw(i, j, newSize, true); // 공백 그리기
                } else {
                    draw(i, j, newSize, false);
                }
            }
        }
    }
}