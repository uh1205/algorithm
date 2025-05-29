import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String bomb = br.readLine();
        int bombLen = bomb.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            // 현재 문자열의 끝부분이 폭발 문자열과 같으면 제거
            if (sb.length() >= bombLen) {
                boolean isBoom = true;
                for (int j = 0; j < bombLen; j++) {
                    if (sb.charAt(sb.length() - bombLen + j) != bomb.charAt(j)) {
                        isBoom = false;
                        break;
                    }
                }

                if (isBoom) {
                    sb.delete(sb.length() - bombLen, sb.length());
                }
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}