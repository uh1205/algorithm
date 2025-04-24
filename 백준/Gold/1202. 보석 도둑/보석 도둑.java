import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 수
        int K = Integer.parseInt(st.nextToken()); // 가방 수

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }
        Arrays.sort(jewels); // 보석 무게 오름차순 정렬

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags); // 가방 무게 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙 (가격 기준)
        long totalValue = 0;
        int j = 0;

        for (int i = 0; i < K; i++) {
            while (j < N && bags[i] >= jewels[j].weight) {
                pq.add(jewels[j].value); // 현재 가방에 담을 수 있는 보석
                j++;
            }

            if (!pq.isEmpty()) {
                totalValue += pq.poll(); // 가장 가치 있는 보석 선택
            }
        }

        System.out.println(totalValue);
    }

    static class Jewel implements Comparable<Jewel> {
        int weight, value;

        Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(this.weight, o.weight); // 무게 기준 정렬
        }
    }
}