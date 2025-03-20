import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            String s = br.readLine();
            int open = 0;
            
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    open++;
                } else {
                    if (open == 0) {
                        open = -1;
                        break;
                    }
                    open--;
                }
            }
            
            sb.append((open == 0) ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }
}