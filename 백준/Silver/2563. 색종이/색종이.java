import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[100][100];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    arr[X + j][Y + k] = 1;
                }
            }
        }
        int result = 0;
        for (int[] row : arr) {
            for (int r : row) {
//                result += r;
                if (r == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}