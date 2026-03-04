import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        int sum = 0;
        int time = 0;
        int idx = 0;
        int count = 0;
        
        while (count < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= time) {
                pq.offer(jobs[idx++]);
            }
            
            if (pq.isEmpty()) {
                time = jobs[idx][0];
            } else {
                int[] job = pq.poll();
                time += job[1];
                sum += time - job[0];
                count++;
            }
        }
        
        return sum / jobs.length;
    }
}