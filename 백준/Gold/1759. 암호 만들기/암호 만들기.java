import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] letters;
    static char[] password;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        password = new char[L];
        letters = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            letters[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(letters);
        combination(0, 0);
        System.out.println(result);
    }

    static void combination(int depth, int start) {
        if (depth == L) {
            addPassword();
            return;
        }
        for (int i = start; i < C; i++) {
            password[depth] = letters[i];
            combination(depth + 1, i + 1); // start가 아니라 i
        }
    }

    static void addPassword() {
        int vowel = 0;
        int consonant = 0;
        for (char p : password) {
            if (p == 'a' || p == 'e' || p == 'i' || p == 'o' || p == 'u') {
                vowel++;
            } else {
                consonant++;
            }
        }
        if (vowel >= 1 && consonant >= 2) {
            result.append(password).append('\n');
        }
    }

}