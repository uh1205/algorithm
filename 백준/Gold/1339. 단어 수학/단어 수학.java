import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Character, Integer> weightMap = new HashMap<>();
        String[] words = new String[N];

        // 입력 및 가중치 계산
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            int len = words[i].length();
            for (int j = 0; j < len; j++) {
                char c = words[i].charAt(j);
                weightMap.put(c, weightMap.getOrDefault(c, 0) + (int) Math.pow(10, len - j - 1));
            }
        }

        // 가중치 높은 순으로 정렬
        List<Map.Entry<Character, Integer>> weightList = new ArrayList<>(weightMap.entrySet());
        weightList.sort((a, b) -> b.getValue() - a.getValue());

        // 숫자 할당 (9부터 차례대로)
        Map<Character, Integer> numMap = new HashMap<>();
        int num = 9;
        for (Map.Entry<Character, Integer> entry : weightList) {
            numMap.put(entry.getKey(), num--);
        }

        // 총합 계산
        int total = 0;
        for (String word : words) {
            int value = 0;
            for (char c : word.toCharArray()) {
                value = value * 10 + numMap.get(c);
            }
            total += value;
        }

        System.out.println(total);
    }
}