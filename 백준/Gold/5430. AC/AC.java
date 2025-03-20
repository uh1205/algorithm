import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            String p = br.readLine(); // 수행할 함수
            int n = Integer.parseInt(br.readLine()); // 배열 크기

            // 배열 파싱 ([], [1,2,3] 같은 형태에서 숫자만 추출)
            Deque<Integer> deque = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            while (n-- > 0) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            boolean reverse = false; // 뒤집힘 여부 플래그
            boolean error = false;   // 에러 발생 여부

            for (char command : p.toCharArray()) {
                if (command == 'R') {
                    reverse = !reverse; // 뒤집기 플래그 토글
                } else { // 'D' 연산
                    if (deque.isEmpty()) {
                        error = true;
                        break;
                    }
                    if (reverse) {
                        deque.pollLast(); // 뒤에서 제거
                    } else {
                        deque.pollFirst(); // 앞에서 제거
                    }
                }
            }

            if (error) {
                sb.append("error\n");
            } else {
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(reverse ? deque.pollLast() : deque.pollFirst());
                    if (!deque.isEmpty()) sb.append(",");
                }
                sb.append("]\n");
            }
        }
        System.out.print(sb);
    }
}