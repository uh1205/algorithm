import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int sum = 0, temp = 0;
        int i = 0, j = arr.length - 1;
        boolean nFlag = false, pFlag = false;

        while (i <= j) {
            if (arr[i] < 0) {
                if (nFlag) sum += temp * arr[i]; // 짝지을 음수가 있는 경우 곱하기
                else temp = arr[i]; // 없으면 임시로 저장
                nFlag = !nFlag;
                i++;
            } else if (arr[i] == 0) {
                if (nFlag) temp = 0; // 하나 남은 음수와 0을 곱해서 더하기
                i++;
            } else {
                if (nFlag) {
                    sum += temp; // 임시로 저장한 음수 더하기
                    nFlag = false;
                }
                // i: 양수 시작 인덱스, j: 양수 끝 인덱스
                if (pFlag) {
                    int s = temp + arr[j];
                    int m = temp * arr[j];
                    sum += Math.max(s, m); // 더하는 것과 곱하는 것 중 큰 값 더하기
                } else {
                    temp = arr[j];
                }
                pFlag = !pFlag;
                j--;
            }
        }

        // 임시값 더하기
        if (nFlag || pFlag) sum += temp;

        System.out.println(sum);
    }
}