import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String str = br.readLine();
            sb.append(judgePalindrome(str)).append('\n');
        }

        System.out.println(sb);
    }

    // 회문이면 0, 유사회문이면 1, 그 외는 2 return
    static int judgePalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                // 왼쪽 문자 생략 후 남은 부분이 회문인지 확인
                boolean b1 = checkPalindrome(str, left + 1, right);

                // 오른쪽 문자 생략 후 남은 부분이 회문인지 확인
                boolean b2 = checkPalindrome(str, left, right - 1);

                // 둘 중 하나라도 참이면 유사회문(1), 아니면 일반 문자열(2)
                if (b1 || b2) {
                    return 1;
                } else {
                    return 2;
                }
            }
            
            left++;
            right--;
        }
        
        return 0;
    }

    static boolean checkPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}