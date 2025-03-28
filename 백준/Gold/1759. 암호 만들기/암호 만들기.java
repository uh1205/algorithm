import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] letters;
    static List<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        letters = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            letters[i] = st.nextToken().charAt(0);
        }

        // 2. 문자 정렬 (사전순)
        Arrays.sort(letters);

        // 3. 백트래킹 실행
        backtrack(0, 0, "", 0, 0);

        // 4. 결과 출력
        for (String password : result) {
            System.out.println(password);
        }
    }

    static void backtrack(int index, int count, String current, int vowels, int consonants) {
        // 1) 종료 조건: L개 선택한 경우
        if (count == L) {
            if (vowels >= 1 && consonants >= 2) {  // 유효한 암호인지 확인
                result.add(current);
            }
            return;
        }

        // 2) 백트래킹 탐색
        for (int i = index; i < C; i++) {
            char ch = letters[i];
            if (isVowel(ch)) {
                backtrack(i + 1, count + 1, current + ch, vowels + 1, consonants);
            } else {
                backtrack(i + 1, count + 1, current + ch, vowels, consonants + 1);
            }
        }
    }

    static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }
}