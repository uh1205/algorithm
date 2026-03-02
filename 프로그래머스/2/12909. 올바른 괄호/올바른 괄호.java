class Solution {
    boolean solution(String s) {
        int val = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                val++;
            } else if (ch == ')') {
                val--;
            }
            if (val < 0) {
                return false;
            }
        }
        
        if (val != 0) {
            return false;
        }
        
        return true;
    }
}