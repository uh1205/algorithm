import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points[i] = new Point(x, y);
        }

        double area = 0;

        for (int i = 0; i < N; i++) {
            Point p1 = points[i];
            Point p2 = points[(i + 1) % N]; // 다음 점 (마지막 점이면 첫 점으로 순환)
            area += (p1.x * p2.y) - (p1.y * p2.x);
        }

        area = Math.abs(area) / 2.0;

        System.out.printf("%.1f\n", area);
    }
}