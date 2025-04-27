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
            if (arr[i] <= 0) {
                if (nFlag) sum += temp * arr[i];
                else temp = arr[i];
                nFlag = !nFlag;
                i++;
            } else {
                // 임시로 저장한 음수가 있는 경우
                if (nFlag) {
                    sum += temp;
                    nFlag = false;
                }
                if (pFlag) sum += Math.max(temp + arr[j], temp * arr[j]);
                else temp = arr[j];
                pFlag = !pFlag;
                j--;
            }
        }

        // 임시값 더하기
        if (nFlag || pFlag) sum += temp;

        System.out.println(sum);
    }
}