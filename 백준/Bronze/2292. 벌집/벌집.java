import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result;
        int tmp = 2;
        for (int i = 1; ; i++) {
            if (N < tmp) {
                result = i;
                break;
            }
            tmp = tmp + 6 * i;
        }
        System.out.println(result);
    }
}