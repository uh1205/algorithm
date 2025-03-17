import java.io.*;
import java.util.*;

public class Main {
    static long[] arr, segTree;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        // 입력 최적화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        N = Integer.parseInt(st.nextToken()); // 수 개수
        M = Integer.parseInt(st.nextToken()); // 변경 연산 횟수
        K = Integer.parseInt(st.nextToken()); // 구간 합 연산 횟수

        arr = new long[N + 1]; // 원본 배열 (1-based index)
        segTree = new long[4 * N]; // 세그먼트 트리 (충분한 크기)

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 초기화
        build(1, 1, N);

        // 연산 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (type == 1) {
                // 변경 연산: b번째 값을 c로 변경
                update(1, 1, N, b, c - arr[b]);
                arr[b] = c; // 원본 배열 값도 변경
            } else {
                // 구간 합 연산: b~c 합 구하기
                bw.write(query(1, 1, N, b, (int) c) + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // 세그먼트 트리 초기화 (O(N))
    static long build(int node, int start, int end) {
        if (start == end) {
            return segTree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return segTree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }

    // 세그먼트 트리 업데이트 (O(log N))
    static void update(int node, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) return; // 범위 밖이면 return
        segTree[node] += diff; // 현재 구간의 합 업데이트
        if (start != end) { // 리프 노드가 아니라면 자식도 변경
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    // 구간 합 쿼리 (O(log N))
    static long query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return 0; // 범위를 벗어남
        if (left <= start && end <= right) return segTree[node]; // 완전히 포함되면 반환

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
    }
}