import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 개수
        int K = Integer.parseInt(st.nextToken()); // 가방 개수

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 보석 무게
            int v = Integer.parseInt(st.nextToken()); // 보석 가치
            jewels[i] = new Jewel(w, v);
        }
        Arrays.sort(jewels); // 무게 기준 오름차순

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine()); // 담을 수 있는 최대 무게
        }
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long totalValue = 0; // 훔칠 수 있는 보석 가격의 합의 최댓값

        for (int i = 0, j = 0; i < K; i++) { // i: 가방 인덱스, j: 보석 인덱스
            // 현재 가방이 담을 수 있는 모든 보석의 가치 큐에 넣기
            while (j < N && bags[i] >= jewels[j].weight) {
                pq.add(jewels[j].value);
                j++;
            }

            if (!pq.isEmpty()) {
                totalValue += pq.poll(); // 가장 가치가 높은 보석 훔치기
            }
        }

        System.out.println(totalValue);
    }

    static class Jewel implements Comparable<Jewel> {
        int weight, value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight - o.weight; // 무게 기준 오름차순
        }
    }
}