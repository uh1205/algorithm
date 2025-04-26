import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 숫자 카드 묶음 개수

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int compareCount = 0;

        while (!pq.isEmpty()) {
            int first = pq.poll(); // 가장 작은 묶음
            if (!pq.isEmpty()) {
                int sum = first + pq.poll(); // 가장 작은 묶음과 두 번째로 작은 묶음 합치기
                compareCount += sum;
                pq.add(sum); // 합친 묶음을 다시 힙에 넣기
            }
        }

        System.out.println(compareCount);
    }
}