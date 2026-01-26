import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int maxResult = Integer.MIN_VALUE;
    static List<Integer> nums = new ArrayList<>();
    static List<Character> ops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String line = br.readLine();

        for (int i = 0; i < N; i++) {
            char c = line.charAt(i);
            if (i % 2 == 0) {
                nums.add(c - '0');
            } else {
                ops.add(c);
            }
        }

        dfs(nums.get(0), 0);

        System.out.println(maxResult);
    }

    static void dfs(int curVal, int opIdx) {
        if (opIdx >= ops.size()) {
            maxResult = Math.max(maxResult, curVal);
            return;
        }

        // 1. 괄호 없이 진행
        int val1 = calculate(curVal, nums.get(opIdx + 1), ops.get(opIdx));
        dfs(val1, opIdx + 1);

        // 2. 괄호 치고 진행
        if (opIdx + 1 < ops.size()) {
            int bracketVal = calculate(nums.get(opIdx + 1), nums.get(opIdx + 2), ops.get(opIdx + 1));
            int val2 = calculate(curVal, bracketVal, ops.get(opIdx));
            dfs(val2, opIdx + 2);
        }
    }

    static int calculate(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        }
        return a * b;
    }
}
