import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        while ((n = Integer.parseInt(br.readLine())) != -1) {
            StringBuilder sb = new StringBuilder();
            sb.append(n).append(" = ").append(1);
            int sum = 1;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    sb.append(" + ").append(i);
                    sum += i;
                }
            }
            if (sum != n) {
                System.out.println(n + " is NOT perfect.");
            } else {
                System.out.println(sb);
            }
        }
    }
}