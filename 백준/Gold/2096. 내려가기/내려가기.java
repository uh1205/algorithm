import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        maxDp[0] = minDp[0] = arr[0][0];
        maxDp[1] = minDp[1] = arr[0][1];
        maxDp[2] = minDp[2] = arr[0][2];

        for (int i = 1; i < N; i++) {
            int max0 = maxDp[0], max1 = maxDp[1], max2 = maxDp[2];
            int min0 = minDp[0], min1 = minDp[1], min2 = minDp[2];

            maxDp[0] = arr[i][0] + Math.max(max0, max1);
            maxDp[1] = arr[i][1] + Math.max(max0, Math.max(max1, max2));
            maxDp[2] = arr[i][2] + Math.max(max1, max2);

            minDp[0] = arr[i][0] + Math.min(min0, min1);
            minDp[1] = arr[i][1] + Math.min(min0, Math.min(min1, min2));
            minDp[2] = arr[i][2] + Math.min(min1, min2);
        }

        int max = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int min = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));

        System.out.println(max + " " + min);
    }
}
