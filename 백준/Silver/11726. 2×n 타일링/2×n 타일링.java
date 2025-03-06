import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            if (i < 4) {
                arr[i] = i;
            } else {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 10007;
            }
        }
        System.out.println(arr[N] % 10007);
    }
}