import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0, sum = 0, minLen = Integer.MAX_VALUE;

        while (right <= N && left <= right) {
            if (sum >= S) {
                minLen = Math.min(minLen, right - left);
                sum -= arr[left];
                left++;
            } else {
                sum += arr[right];
                right++;
            }
        }

        if (minLen == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLen);
    }
}