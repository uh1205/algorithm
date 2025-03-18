import java.io.*;
import java.util.*;

class Meet implements Comparable<Meet> {
    int s, e;

    Meet(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public int compareTo(Meet o) {
        if (this.e != o.e) {
            return this.e - o.e;
        }
        return this.s - o.s;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Meet> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Meet(s, e));
        }

        int last = pq.poll().e;
        int cnt = 1;

        while (!pq.isEmpty()) {
            Meet m = pq.poll();
            if (m.s < last) continue;
            last = m.e;
            cnt++;
        }

        System.out.println(cnt);
    }
}