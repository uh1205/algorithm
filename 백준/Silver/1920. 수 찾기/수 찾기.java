import java.io.*;
import java.util.*;

class Main {

    static int N, M;
    static int[] nums, targets;

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

        StringBuilder sb = new StringBuilder();
        for (int target : targets) {
            int result = Arrays.binarySearch(nums, target);
            if (result < 0) {
                sb.append(0);
            } else {
                sb.append(1);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}