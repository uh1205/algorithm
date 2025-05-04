import java.io.*;
import java.util.*;

public class Main {
    static boolean[] broken = new boolean[10]; // 고장난 버튼 표시

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 목표 채널
        int M = Integer.parseInt(br.readLine()); // 고장난 버튼 수

        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int b = Integer.parseInt(st.nextToken());
                broken[b] = true;
            }
        }

        int minPress = Math.abs(N - 100); // +, -만 사용하는 경우

        // 0부터 999999까지 모두 시도 (6자리까지 가능)
        for (int i = 0; i <= 999999; i++) {
            int len = canPress(i);
            if (len > 0) {
                int press = len + Math.abs(i - N);
                minPress = Math.min(minPress, press);
            }
        }

        System.out.println(minPress);
    }

    // 숫자 i를 누를 수 있는지 확인. 누를 수 있으면 자릿수 반환, 아니면 0
    static int canPress(int num) {
        if (num == 0) {
            return broken[0] ? 0 : 1;
        }

        int len = 0;
        while (num > 0) {
            if (broken[num % 10]) return 0;
            num /= 10;
            len++;
        }
        return len;
    }
}