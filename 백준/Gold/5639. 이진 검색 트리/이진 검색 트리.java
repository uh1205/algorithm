import java.io.*;

public class Main {
    static int[] preorder = new int[10001];
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            preorder[idx++] = Integer.parseInt(line);
        }

        postOrder(0, idx - 1);
    }

    // 후위 순회 출력
    static void postOrder(int start, int end) {
        if (start > end) return;

        int root = preorder[start];
        int mid = start + 1;

        // 왼쪽 서브트리 범위 탐색 (root보다 작을 때까지)
        while (mid <= end && preorder[mid] < root) {
            mid++;
        }

        // 왼쪽 재귀
        postOrder(start + 1, mid - 1);
        // 오른쪽 재귀
        postOrder(mid, end);
        // 루트 출력
        System.out.println(root);
    }
}