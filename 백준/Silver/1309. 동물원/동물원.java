import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int threeCount = 1;
        int twoCount = 0;

        for (int i = 2; i <= N; i++) {
            int next3 = (threeCount + twoCount) % MOD;
            int next2 = (2 * threeCount + twoCount) % MOD;
            threeCount = next3;
            twoCount = next2;
        }

        int result = (threeCount * 3 + twoCount * 2) % MOD;
        System.out.println(result);
    }
}