import java.io.*;
import java.util.*;

public class Main {
    static int[] preorder, inorder;
    static int n;
    static Map<Integer, Integer> inIndex;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());

            preorder = new int[n];
            inorder = new int[n];
            inIndex = new HashMap<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) preorder[i] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                inIndex.put(inorder[i], i); // 값 → inorder 인덱스 저장
            }

            buildPostorder(0, n - 1, 0, n - 1);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    // 전위 순회 [preL..preR], 중위 순회 [inL..inR] 범위를 처리
    static void buildPostorder(int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) return;

        int root = preorder[preL];        // 현재 루트
        int rootIdx = inIndex.get(root);  // inorder에서 루트 위치
        int leftSize = rootIdx - inL;     // 왼쪽 서브트리 크기

        // 왼쪽 서브트리
        buildPostorder(preL + 1, preL + leftSize, inL, rootIdx - 1);

        // 오른쪽 서브트리
        buildPostorder(preL + leftSize + 1, preR, rootIdx + 1, inR);

        // 후위 순회: 루트 출력
        sb.append(root).append(" ");
    }
}