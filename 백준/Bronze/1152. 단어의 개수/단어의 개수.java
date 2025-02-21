import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int sl = str.length();
        int answer = 0;
        for (int i = 0; i < sl; i++) {
            if (i != 0 && str.charAt(i) == ' ') {
                answer++;
            }
        }
        if (str.charAt(sl - 1) != ' ') {
            answer++;
        }
        System.out.println(answer);
    }
}