import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static int T;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> countMap = new HashMap<>();

            for (int i = 0; i < k; i++) {
                String[] command = br.readLine().split(" ");
                char op = command[0].charAt(0);
                int num = Integer.parseInt(command[1]);

                if (op == 'I') {
                    minHeap.offer(num);
                    maxHeap.offer(num);
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                } else if (op == 'D') {
                    if (num == 1) {
                        remove(maxHeap, countMap);
                    } else {
                        remove(minHeap, countMap);
                    }
                }
            }

            Integer max = getValidTop(maxHeap, countMap);
            Integer min = getValidTop(minHeap, countMap);

            if (max == null || min == null) {
                bw.write("EMPTY\n");
            } else {
                bw.write(max + " " + min + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // 유효한 값 삭제
    static void remove(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int val = pq.poll();
            if (map.containsKey(val)) {
                map.put(val, map.get(val) - 1);
                if (map.get(val) == 0) map.remove(val);
                break;
            }
        }
    }

    // 유효한 top 값 반환
    static Integer getValidTop(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int val = pq.peek();
            if (map.containsKey(val)) return val;
            pq.poll();
        }
        return null;
    }
}