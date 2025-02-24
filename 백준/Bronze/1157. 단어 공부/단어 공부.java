import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().toUpperCase();
        int[] values = new int[26];
        int mv = 0;
        int mi = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 65;
            int value = ++values[index];
            if (value > mv) {
                mv = value;
                mi = index;
            }
        }
        int cnt = 0;
        for (int value : values) {
            if (value == mv) {
                cnt++;
            }
        }
        if (cnt > 1) {
            System.out.println("?");
        } else {
            System.out.println((char) ('A' + mi));
        }
    }
}
