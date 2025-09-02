import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i : new int[]{2, 3, 5, 7}) {
            dfs(1, i);
        }

        System.out.println(result);
    }

    static void dfs(int depth, int num) {
        if (depth == N) {
            result.append(num).append('\n');
            return;
        }
        for (int i : new int[]{1, 3, 7, 9}) {
            int next = num * 10 + i;
            if (isPrime(next)) {
                dfs(depth + 1, next);
            }
        }
    }

    static boolean isPrime(int num) {
//        if (num < 2) return false;
//        if (num == 2 || num == 3) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}