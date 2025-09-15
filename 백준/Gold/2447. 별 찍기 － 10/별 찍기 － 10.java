import java.io.*;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        draw(N, 0, 0, false);

        StringBuilder sb = new StringBuilder();
        for (char[] chars : map) {
            sb.append(chars).append('\n');
        }

        System.out.println(sb);
    }

    static void draw(int n, int r, int c, boolean blank) {
        if (blank) {
            for (int i = r; i < r + n; i++) {
                for (int j = c; j < c + n; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (n == 1) {
            map[r][c] = '*';
            return;
        }

        int size = n / 3;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count++;
                if (count == 5) {
                    draw(size, r + i * size, c + j * size, true);
                } else {
                    draw(size, r + i * size, c + j * size, false);
                }
            }
        }
    }
}