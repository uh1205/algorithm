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

        List<Integer> dp = new ArrayList<>();

        for (int num : A) {
            int idx = Collections.binarySearch(dp, num);
            if (idx < 0) {
                idx = -idx - 1;
            }
            if (idx == dp.size()) {
                dp.add(num);
            } else {
                dp.set(idx, num);
            } 
        }

        System.out.println(dp.size());
    }
}