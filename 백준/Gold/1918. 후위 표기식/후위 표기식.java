import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>(); // 연산자 스택

        for (char c : infix.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c); // 피연산자는 그대로 출력
            } else if (c == '(') {
                stack.push(c); // '('는 무조건 push
            } else if (c == ')') {
                // '('가 나올 때까지 연산자 모두 pop하여 출력
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop(); // '(' 제거
            } else {
                // 연산자는 스택에 push하되, 스택 안의 연산자가 더 우선순위 높으면 pop해서 출력
                while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // 남은 연산자 모두 출력
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

    static int getPriority(char op) {
        if (op == '*' || op == '/') return 2;
        if (op == '+' || op == '-') return 1;
        return 0; // '('일 경우
    }
}