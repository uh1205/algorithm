import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 바구니 개수
        int M = sc.nextInt(); // 공을 넣는 횟수
        int[] basket = new int[N]; // 바구니 배열 (초기값: 0)

        for (int m = 0; m < M; m++) {
            int i = sc.nextInt() - 1; // 시작 바구니 (0-based index)
            int j = sc.nextInt() - 1; // 끝 바구니
            int k = sc.nextInt(); // 넣을 공의 번호

            // i부터 j까지 k번 공을 넣기
            for (int idx = i; idx <= j; idx++) {
                basket[idx] = k;
            }
        }
        sc.close();

        // 출력 최적화를 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        for (int num : basket) {
            sb.append(num).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}