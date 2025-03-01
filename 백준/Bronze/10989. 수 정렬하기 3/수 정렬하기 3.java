import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 1 ~ 10,000
        int[] arr = new int[10000];

        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine()) - 1]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                sb.append(i + 1).append("\n");
            }
        }
        System.out.println(sb);
    }
}