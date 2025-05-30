import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();
        int bombLen = bomb.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));

            // 마지막 부분이 bomb과 일치하는지 확인
            int sbLen = sb.length();
            if (sbLen >= bombLen) {
                boolean isBomb = true;
                for (int j = 0; j < bombLen; j++) {
                    if (sb.charAt(sbLen - bombLen + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    sb.setLength(sbLen - bombLen); // 문자열 폭발
                }
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}