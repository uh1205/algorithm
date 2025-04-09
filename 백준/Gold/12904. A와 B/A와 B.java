import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder(T);
        boolean reverse = false;

        // 뒤에 A 추가 -> 뒤에 A 제거
        // 뒤집고 뒤에 B 추가 -> 뒤에 B 제거 후 뒤집기
        while (sb.length() > S.length()) {
            int lastIdx = reverse ? 0 : sb.length() - 1;

            if (sb.charAt(lastIdx) == 'A') {
                sb.deleteCharAt(lastIdx);
            } else if (sb.charAt(lastIdx) == 'B') {
                sb.deleteCharAt(lastIdx);
                reverse = !reverse;
            } else break;
        }

        if (reverse) sb.reverse();
        System.out.println(S.contentEquals(sb) ? 1 : 0);
        // S.equals(sb.toString()) == S.contentEquals(sb)
    }
}