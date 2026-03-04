import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        
        Queue<Task> waitQ = new ArrayDeque<>();
        for (int i = 0; i < jobs.length; i++) {
            waitQ.offer(new Task(i, jobs[i][0], jobs[i][1]));
        }
        
        int sum = 0;
        int time = 0;
        int count = 0;
        
        PriorityQueue<Task> pq = new PriorityQueue<>();
        
        while (count < jobs.length) {
            while (!waitQ.isEmpty() && waitQ.peek().req <= time) {
                pq.offer(waitQ.poll());
            }
            
            if (pq.isEmpty()) {
                time++;
                continue;
            }
            
            Task task = pq.poll();
            count++;
            
            time += task.take;
            sum += time - task.req;
        }
        
        return sum / jobs.length;
    }
    
    static class Task implements Comparable<Task> {
        int num, req, take;
        
        Task(int num, int req, int take) {
            this.num = num;
            this.req = req;
            this.take = take;
        }
        
        public int compareTo(Task o) {
            if (this.take == o.take) {
                if (this.req == o.req) {
                    return this.num - o.num;
                }
                return this.req - o.req;
            }
            return this.take - o.take;
        }
    }
}