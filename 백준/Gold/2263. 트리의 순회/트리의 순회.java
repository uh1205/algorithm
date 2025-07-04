import java.io.*;
import java.util.*;

public class Main {
    static int[] inOrder, postOrder;
    static int[] preOrder;
    static Map<Integer, Integer> inIndexMap;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        inOrder = new int[n];
        postOrder = new int[n];
        inIndexMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            inIndexMap.put(inOrder[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        buildPreOrder(0, n - 1, 0, n - 1);
        System.out.println(sb);
    }

    static void buildPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) return;

        int rootVal = postOrder[postEnd];
        sb.append(rootVal).append(' ');

        int rootIndex = inIndexMap.get(rootVal);
        int leftSize = rootIndex - inStart;

        // 왼쪽 서브트리
        buildPreOrder(inStart, rootIndex - 1, postStart, postStart + leftSize - 1);

        // 오른쪽 서브트리
        buildPreOrder(rootIndex + 1, inEnd, postStart + leftSize, postEnd - 1);
    }
}