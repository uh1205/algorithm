import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String str = br.readLine();
            sb.append(checkPalindrome(str)).append('\n');
        }
        System.out.print(sb);
    }

    static int checkPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                // 한 문자 제거하고 회문인지 확인
                if (isPalindrome(str, left + 1, right) || isPalindrome(str, left, right - 1)) {
                    return 1; // 유사회문
                } else {
                    return 2; // 일반 문자열
                }
            }
        }
        return 0; // 완전한 회문
    }

    static boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}