import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(start, end);
        }

        Arrays.sort(lectures); // 시작 시간 기준 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 끝나는 시간 기준 최소 힙
        pq.add(lectures[0].end);

        for (int i = 1; i < N; i++) {
            // 가장 빨리 끝나는 수업이 새 수업 시작 전에 끝났으면 제거
            if (pq.peek() <= lectures[i].start) {
                pq.poll(); // 강의실 재사용
            }
            pq.add(lectures[i].end); // 새 수업 종료 시간 등록
        }

        System.out.println(pq.size()); // 필요한 강의실 수
    }

    static class Lecture implements Comparable<Lecture> {
        int start, end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }
    }
}