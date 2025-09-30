import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(countOnes(B) - countOnes(A - 1));
    }

    static long countOnes(long n) {
        if (n <= 0) return 0;
        long sum = 0;
        for (int i = 0; i <= 60; i++) { // n ≤ 10^16 → 10^18 → 2^60
            long cycle = 1L << (i + 1);
            long fullCycles = (n + 1) / cycle;
            long remainder = (n + 1) % cycle;
            sum += fullCycles * (1L << i) + Math.max(0, remainder - (1L << i));
        }
        return sum;
    }
}