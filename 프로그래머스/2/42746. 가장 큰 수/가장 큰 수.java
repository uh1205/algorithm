import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        int n = numbers.length;
        
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strings, (a, b) -> (b + a).compareTo(a + b));
        
        if (strings[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}