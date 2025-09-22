import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int white = 0, blue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        paper(N, 0, 0);
        
        System.out.println(white);
        System.out.println(blue);
    }

    static void paper(int n, int r, int c) {
        if (isAllSame(n, r, c)) {
            if (map[r][c] == '0') white++;
            else blue++;
            return;
        }
        int half = n / 2;
        paper(half, r, c);
        paper(half, r, c + half);
        paper(half, r + half, c);
        paper(half, r + half, c + half);
    }

    static boolean isAllSame(int n, int r, int c) {
        char first = map[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (map[i][j] != first) return false;
            }
        }
        return true;
    }
}