class Solution {
    public int solution(String s) {
        int minLength = s.length(); // 압축을 안 했을 때의 길이

        for (int unit = 1; unit <= s.length() / 2; unit++) {
            StringBuilder compressed = new StringBuilder();
            String prev = s.substring(0, unit);
            int count = 1;

            for (int i = unit; i < s.length(); i += unit) {
                int end = Math.min(i + unit, s.length());
                String next = s.substring(i, end);

                if (prev.equals(next)) {
                    count++;
                } else {
                    if (count > 1) compressed.append(count);
                    compressed.append(prev);
                    prev = next;
                    count = 1;
                }
            }

            if (count > 1) compressed.append(count);
            compressed.append(prev);

            minLength = Math.min(minLength, compressed.length());
        }

        return minLength;
    }
}