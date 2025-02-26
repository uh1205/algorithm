import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (K == 1) {
            System.out.println(1);
            return;
        }
        for (int i = 2, j = 2; i <= N; i++) {
            if (N % i == 0) {
                if (j == K) {
                    System.out.println(i);
                    return;
                } else {
                    j++;
                }
            }
        }
        System.out.println(0);
    }
}