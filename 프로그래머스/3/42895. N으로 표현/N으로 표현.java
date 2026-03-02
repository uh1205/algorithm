import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        
        Set<Integer>[] sets = new Set[9];
        for (int i = 1; i <= 8; i++) {
            sets[i] = new HashSet<>();
        }
        
        
        for (int i = 1; i <= 8; i++) {
            sets[i].add(Integer.valueOf(String.valueOf(N).repeat(i)));
            
            for (int j = 1; j < i; j++) {
                for (int n1 : sets[j]) {
                    for (int n2 : sets[i - j]) {
                        sets[i].add(n1 + n2);
                        sets[i].add(n1 - n2);
                        sets[i].add(n1 * n2);
                        if (n2 != 0) sets[i].add(n1 / n2);
                    }
                }
            }
            
            if (sets[i].contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}