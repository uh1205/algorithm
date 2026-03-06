import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (String op : operations) {
            String[] split = op.split(" ");
            String cmd = split[0];
            int val = Integer.parseInt(split[1]);
            
            if (cmd.equals("I")) {
                map.put(val, map.getOrDefault(val, 0) + 1);
            } else {
                if (map.isEmpty()) continue;
                
                int del = (val == 1) ? map.lastKey() : map.firstKey();
                
                if (map.get(del) == 1) {
                    map.remove(del);
                } else {
                    map.put(del, map.get(del) - 1);
                }
            }
        }
        
        if (map.isEmpty()) {
            return new int[]{0, 0};
        }
        return new int[]{map.lastKey(), map.firstKey()};
    }
}