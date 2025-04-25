import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        List<Integer> plus = new ArrayList<>();
        List<Integer> minus = new ArrayList<>();

        int ones = 0;
        int zeroCount = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                plus.add(num);
            } else if (num == 1) {
                ones++;
            } else if (num == 0) {
                zeroCount++;
            } else {
                minus.add(num);
            }
        }

        // 양수 > 1 내림차순 정렬
        plus.sort(Collections.reverseOrder());

        // 음수 오름차순 정렬
        minus.sort(Comparator.naturalOrder());

        int sum = 0;

        // 양수 묶기
        for (int i = 0; i < plus.size(); i += 2) {
            if (i + 1 < plus.size()) {
                sum += plus.get(i) * plus.get(i + 1);
            } else {
                sum += plus.get(i);
            }
        }

        // 음수 묶기
        for (int i = 0; i < minus.size(); i += 2) {
            if (i + 1 < minus.size()) {
                sum += minus.get(i) * minus.get(i + 1);
            } else {
                // 음수가 하나 남았는데 0이 있으면 곱해서 없애는 게 이득
                if (zeroCount == 0) {
                    sum += minus.get(i);
                }
            }
        }

        // 1은 무조건 더함
        sum += ones;

        System.out.println(sum);
    }
}