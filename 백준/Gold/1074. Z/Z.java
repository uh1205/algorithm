import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(zOrder(N, r, c));
    }

    static int zOrder(int n, int r, int c) {
        if (n == 0) return 0;

        int half = 1 << (n - 1); // 절반 위치
        int qSize = half * half; // 하나의 사분면이 차지하는 칸 수

        // (r, c)가 어느 사분면에 속하는지 확인
        // 1. 좌상
        if (r < half && c < half) {
            return zOrder(n - 1, r, c);
        }
        // 2. 우상
        else if (r < half && c >= half) {
            return qSize + zOrder(n - 1, r, c - half);
        }
        // 3. 좌하
        else if (r >= half && c < half) {
            return 2 * qSize + zOrder(n - 1, r - half, c);
        }
        // 4. 우하
        else {
            return 3 * qSize + zOrder(n - 1, r - half, c - half);
        }
    }
}