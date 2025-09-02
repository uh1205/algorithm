import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 2; i < 9; i++) {
            if (isPrime(i)) dfs(0, i);
        }

        System.out.println(result);
    }

    static void dfs(int depth, int num) {
        if (depth == N - 1) {
            result.append(num).append('\n');
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (isPrime(num * 10 + i)) {
                num = num * 10 + i;
                dfs(depth + 1, num);
                num /= 10;
            }
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}