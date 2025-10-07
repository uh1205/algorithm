import java.util.*;

class Solution {
    public int solution(String s) {
        int len = s.length();
        int ans = len;

        for (int unit = 1; unit <= len / 2; unit++) {
            int part = len / unit;

            StringBuilder sb = new StringBuilder();

            String prev = s.substring(0, unit);
            int cnt = 1;

            for (int i = 1; i < part; i++) {
                String cur = s.substring(unit * i, unit * i + unit);
                if (cur.equals(prev)) {
                    cnt++;
                } else {
                    if (cnt > 1) sb.append(cnt);
                    sb.append(prev);
                    prev = cur;
                    cnt = 1;
                }
            }

            if (cnt > 1) sb.append(cnt);
            sb.append(prev);

            if (len % unit != 0) {
                sb.append(s.substring(part * unit));
            }

            ans = Math.min(ans, sb.length());
        }

        return ans;
    }
}