import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        int[] weight = new int[26]; // A ~ Z까지의 가중치

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            int len = words[i].length();

            for (int j = 0; j < len; j++) {
                char c = words[i].charAt(j);
                int idx = c - 'A';
                weight[idx] += (int) Math.pow(10, len - j - 1);
            }
        }

        int num = 9, max = -1, idx = -1, sum = 0;

        while (num > 0 && max != 0) {
            max = 0;

            // 가중치가 가장 큰 글자 찾기
            for (int i = 0; i < 26; i++) {
                if (max < weight[i]) {
                    max = weight[i];
                    idx = i;
                }
            }

            // 가중치가 0이 아니라면
            if (max != 0) {
                sum += weight[idx] * num--;
            }
            
            weight[idx] = 0;
        }

        System.out.println(sum);
    }
}