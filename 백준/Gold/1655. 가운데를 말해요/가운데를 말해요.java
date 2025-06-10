import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> numbers = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            int idx = Collections.binarySearch(numbers, input);
            if (idx < 0) idx = -idx - 1;
            numbers.add(idx, input);
            int size = numbers.size();
            int mid = size % 2 == 0
                    ? size / 2 - 1
                    : size / 2;
            sb.append(numbers.get(mid)).append('\n');
        }

        System.out.println(sb);
    }
}