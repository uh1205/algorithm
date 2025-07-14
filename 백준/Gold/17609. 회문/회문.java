import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String str = br.readLine();
            sb.append(classifyPalindrome(str)).append('\n');
        }

        System.out.print(sb);
    }

    static int classifyPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            char lChar = str.charAt(left);
            char rChar = str.charAt(right);

            if (lChar != rChar) {
                boolean skipLeft = isPalindrome(str, left + 1, right);
                boolean skipRight = isPalindrome(str, left, right - 1);

                return (skipLeft || skipRight) ? 1 : 2;
            }

            left++;
            right--;
        }

        return 0;
    }

    // 주어진 구간 [left, right]이 회문인지 검사
    static boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            char lChar = str.charAt(left);
            char rChar = str.charAt(right);

            if (lChar != rChar) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}