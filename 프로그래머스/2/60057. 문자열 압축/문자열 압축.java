class Solution {
    public int solution(String s) {
        int min = Integer.MAX_VALUE;
        int len = s.length();
        
        if (len == 1) return 1;
        if (len == 2) return 2;

        for (int cut = len / 2; cut > 0; cut--) {
            int size = (len % cut == 0) ? len / cut : len / cut + 1;
            String[] arr = new String[size];

            for (int i = 0; i < size; i++) {
                int end = cut * (i + 1);
                if (end < len) {
                    arr[i] = s.substring(cut * i, end);
                } else {
                    arr[i] = s.substring(cut * i);
                }
            }

            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            String word = "";

            for (String str : arr) {
                if (cnt == 0) {
                    word = str;
                    cnt++;
                } else if (str.equals(word)) {
                    cnt++;
                } else {
                    if (cnt > 1) sb.append(cnt);
                    sb.append(word);
                    word = str;
                    cnt = 1;
                }
            }
            
            if (cnt > 1) sb.append(cnt);
            sb.append(word);

            min = Math.min(min, sb.length());
        }

        return min;
    }
}