import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] nums, targets;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        M = Integer.parseInt(br.readLine());
        targets = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }

        for (int target : targets) {
            search(0, N - 1, target);
        }

        System.out.println(sb);
    }

    static void search(int start, int end, int target) {
        if (start == end) {
            if (nums[start] == target) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append('\n');
            return;
        }
        int mid = (start + end) / 2;
        if (nums[mid] < target) {
            search(mid + 1, end, target);
        } else {
            search(start, mid, target);
        }
    }
}
