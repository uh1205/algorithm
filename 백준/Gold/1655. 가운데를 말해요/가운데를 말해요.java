import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 중간값 이하 값을 저장하는 최대 힙 (가장 큰 값이 peek)
        PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 중간값 초과 값을 저장하는 최소 힙 (가장 작은 값이 peek)
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // leftHeap이 비어있거나, num이 중간값 이하일 경우 leftHeap에 삽입
            if (leftHeap.isEmpty() || num <= leftHeap.peek()) {
                leftHeap.add(num);
            } else {
                rightHeap.add(num);
            }

            // 힙 크기 균형 조정
            if (leftHeap.size() > rightHeap.size() + 1) {
                rightHeap.add(leftHeap.poll());
            } else if (rightHeap.size() > leftHeap.size()) {
                leftHeap.add(rightHeap.poll());
            }

            // 현재까지의 중간값은 항상 leftHeap의 루트
            sb.append(leftHeap.peek()).append('\n');
        }

        System.out.print(sb);
    }
}