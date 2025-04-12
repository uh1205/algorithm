import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long distance = y - x;

            long k = (long) Math.sqrt(distance);

            if (distance == k * k) {
                System.out.println(2 * k - 1);
            } else if (distance <= k * (k + 1)) {
                System.out.println(2 * k);
            } else {
                System.out.println(2 * k + 1);
            }
        }
    }
}