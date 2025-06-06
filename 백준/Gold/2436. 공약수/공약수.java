import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int G = Integer.parseInt(st.nextToken()); // 최대공약수
        int L = Integer.parseInt(st.nextToken()); // 최소공배수

        int target = L / G; // x = G*a, y = G*b라 할 때 a*b = L/G 가 되어야 함
        int minX = 0, minY = 0;
        int minSum = Integer.MAX_VALUE;

        // a*b = target이 되도록 하는 (a, b)쌍 중, gcd(a, b) == 1 인 서로소 쌍을 찾는다
        for (int a = 1; a * a <= target; a++) {
            if (target % a != 0) continue; // a가 target의 약수가 아니면 건너뜀

            int b = target / a; // b를 a에 대한 target의 몫으로 설정

            if (gcd(a, b) == 1) { // a, b가 서로소인 경우에만 유효한 조합
                int x = G * a;
                int y = G * b;
                int sum = x + y;

                // x+y가 현재 최소합보다 작다면 갱신
                if (sum < minSum) {
                    minSum = sum;
                    minX = Math.min(x, y); // 작은 값을 앞에, 큰 값을 뒤에 출력해야 하므로 정렬
                    minY = Math.max(x, y);
                }
            }
        }

        System.out.println(minX + " " + minY);
    }

    // 유클리드 호제법으로 최대공약수(GCD)를 구하는 메서드
    static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = a % b; // 나머지를 임시 저장
            a = b;           // a를 b로 갱신
            b = tmp;         // b를 나머지로 갱신
        }
        return a; // b가 0이 되었을 때의 a가 GCD
    }
}