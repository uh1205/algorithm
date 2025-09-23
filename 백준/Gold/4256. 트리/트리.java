import java.io.*;
import java.util.*;

public class Main {
    static int[] preorder, inorder;
    static Map<Integer, Integer> inIdx;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            preorder = new int[n]; // 전위 순회
            inorder = new int[n]; // 중위 순회
            inIdx = new HashMap<>();

            // 전위 순회 결과 (N → L → R)
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            // 중위 순회 결과 (L → N → R)
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                inIdx.put(inorder[i], i); // 값 → 중위 인덱스
            }

            // 후위 순회 결과 출력 (L → R → N)
            postOrder(0, n - 1, 0, n - 1);
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 후위 순회 결과 출력 (L → R → N)
    static void postOrder(int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) return;

        // 전위(N → L → R)의 맨앞(preL)은 현재 루트
        int root = preorder[preL];

        // 중위에서 루트 위치
        int rootIdx = inIdx.get(root);

        // 왼쪽 서브트리 크기
        int leftSize = rootIdx - inL;

        // 왼쪽 서브트리
        postOrder(preL + 1, preL + leftSize, inL, rootIdx - 1);

        // 오른쪽 서브트리
        postOrder(preL + leftSize + 1, preR, rootIdx + 1, inR);

        // 후위 순회: 루트 출력
        sb.append(root).append(' ');
    }
}