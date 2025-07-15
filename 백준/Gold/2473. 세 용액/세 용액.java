import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long minAbs = Long.MAX_VALUE;
        int[] result = new int[3];
        boolean isZero = false;

        for (int i = 0; i <= N - 3; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = (long) arr[i] + arr[left] + arr[right];
                long abs = Math.abs(sum);

                if (abs < minAbs) {
                    minAbs = abs;
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    isZero = true;
                    break;
                }
            }

            if (isZero) break;
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}