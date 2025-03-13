import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int l = 0, r = 0;
        int min = Integer.MAX_VALUE;
        while (r < N && l <= r) {
            int diff = arr[r] - arr[l];

            if (diff < M) {
                r++;
            }
            if (diff >= M) {
                min = Math.min(min, diff);
                l++;
            }
        }

        System.out.println(min);
    }
}