import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] dpInc = new int[N]; // i를 끝으로 하는 증가 수열
        int[] dpDec = new int[N]; // i를 시작으로 하는 감소 수열

        Arrays.fill(dpInc, 1);
        Arrays.fill(dpDec, 1);

        // 증가하는 부분 수열 (LIS)
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    dpInc[i] = Math.max(dpInc[i], dpInc[j] + 1);
                }
            }
        }

        // 감소하는 부분 수열 (LDS)
        for (int i = N - 2; i >= 0; i--) {
            for (int j = N - 1; j > i; j--) {
                if (A[j] < A[i]) {
                    dpDec[i] = Math.max(dpDec[i], dpDec[j] + 1);
                }
            }
        }

        // 최대 바이토닉 부분 수열 길이 계산
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            maxLength = Math.max(maxLength, dpInc[i] + dpDec[i] - 1);
        }

        System.out.println(maxLength);
    }
}