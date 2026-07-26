class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int len = convert(video_len);
        int cur = convert(pos);
        int start = convert(op_start);
        int end = convert(op_end);
        
        if (cur >= start && cur <= end) {
            cur = end;
        }
        
        for (String cmd : commands) {
            if (cmd.equals("prev")) {
                cur = cur - 10 > 0 ? cur - 10 : 0;
            }
            else if (cmd.equals("next")) {
                cur = cur + 10 < len ? cur + 10 : len;
            }
        if (cur >= start && cur <= end) {
            cur = end;
        }   
        }
                if (cur >= start && cur <= end) {
            cur = end;
        }
        
        int m = cur / 60;
        int s = cur % 60;
        
        String mm = m > 10 ? String.valueOf(m) : "0" + String.valueOf(m);
        String ss = s > 10 ? String.valueOf(s) : "0" + String.valueOf(s);
        
        return mm + ":" + ss;
    }
    
    int convert(String pos) {
        String[] sp = pos.split(":");
        return Integer.parseInt(sp[0]) * 60 + Integer.parseInt(sp[1]);
    }
}