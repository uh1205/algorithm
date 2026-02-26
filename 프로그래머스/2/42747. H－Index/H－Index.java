class Solution {
    public int solution(int[] citations) {
        int h = citations.length;
        while (true) {
            int over = 0; // h번 이상 인용된 논문 수
            int under = 0; // h번 이하 인용된 논문 수
            
            for (int c : citations) {
                if (c >= h) over++;
                if (c < h) under++;
            }
            
            if (over >= h && under <= h) break;
            
            h--;
        }
        return h;
    }
}