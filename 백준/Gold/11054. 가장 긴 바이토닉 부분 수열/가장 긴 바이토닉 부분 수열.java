import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        int[] LIS = new int[N];
        int[] LDS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // LIS: 왼쪽에서부터 증가하는 부분 수열
        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }

        // LDS: 오른쪽에서부터 감소하는 부분 수열
        for (int i = N - 1; i >= 0; i--) {
            LDS[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (A[j] < A[i]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        // 가장 긴 바이토닉 수열 길이 계산
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            maxLength = Math.max(maxLength, LIS[i] + LDS[i] - 1);
        }

        System.out.println(maxLength);
    }
}