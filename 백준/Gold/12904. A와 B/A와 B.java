import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        // 뒤에 A 추가 -> 뒤에 A 제거
        // 뒤집고 뒤에 B 추가 -> 뒤에 B 제거 후 뒤집기
        StringBuilder sb = new StringBuilder(T);
        while (sb.length() > S.length()) {
            int lastIdx = sb.length() - 1;
            if (sb.charAt(lastIdx) == 'A') sb.deleteCharAt(lastIdx);
            else if (sb.charAt(lastIdx) == 'B') {
                sb.deleteCharAt(lastIdx);
                sb.reverse();
            }
            else break;
        }

        System.out.println(S.contentEquals(sb) ? 1 : 0);
    }
}