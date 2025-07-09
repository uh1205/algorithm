import java.io.*;
import java.util.*;

public class Main {
    static int[] preorder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            list.add(Integer.parseInt(line));
        }

        preorder = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            preorder[i] = list.get(i);
        }

        postorder(0, preorder.length - 1);

        System.out.print(sb);
    }

    static void postorder(int start, int end) {
        if (start > end) return;

        int root = preorder[start];
        int mid = start + 1;

        // 오른쪽 서브트리 시작점 찾기
        while (mid <= end && preorder[mid] < root) {
            mid++;
        }

        // 왼쪽 서브트리
        postorder(start + 1, mid - 1);

        // 오른쪽 서브트리
        postorder(mid, end);

        // 후위 순회: 루트 마지막
        sb.append(root).append('\n');
    }
}