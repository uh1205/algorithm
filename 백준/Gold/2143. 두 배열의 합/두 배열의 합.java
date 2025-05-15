import java.io.*;
import java.util.*;

public class Main {

    static int T, n, m;
    static int[] A, B;
    static List<Long> subA = new ArrayList<>();
    static List<Long> subB = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        B = new int[m];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 부배열 합 계산
        getSubarraySums(A, subA);
        getSubarraySums(B, subB);

        // subB 정렬
        Collections.sort(subB);

        long count = 0;

        // subA의 각 원소 a에 대해, T - a가 subB에 몇 개 있는지 계산
        for (long a : subA) {
            long target = T - a;
            int lower = lowerBound(subB, target);
            int upper = upperBound(subB, target);
            count += (upper - lower);
        }

        System.out.println(count);
    }

    static void getSubarraySums(int[] arr, List<Long> list) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            long sum = 0;
            for (int j = i; j < len; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }
    }

    static int lowerBound(List<Long> list, long target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int upperBound(List<Long> list, long target) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}