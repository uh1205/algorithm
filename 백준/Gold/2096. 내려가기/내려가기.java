import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        int[] tempMax = new int[3];
        int[] tempMin = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                maxDp[0] = minDp[0] = a;
                maxDp[1] = minDp[1] = b;
                maxDp[2] = minDp[2] = c;
            } else {
                tempMax[0] = Math.max(maxDp[0], maxDp[1]) + a;
                tempMax[1] = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]) + b;
                tempMax[2] = Math.max(maxDp[1], maxDp[2]) + c;

                tempMin[0] = Math.min(minDp[0], minDp[1]) + a;
                tempMin[1] = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]) + b;
                tempMin[2] = Math.min(minDp[1], minDp[2]) + c;

                for (int j = 0; j < 3; j++) {
                    maxDp[j] = tempMax[j];
                    minDp[j] = tempMin[j];
                }
            }
        }

        int maxScore = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int minScore = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);

        System.out.println(maxScore + " " + minScore);
    }
}