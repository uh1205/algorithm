import java.io.*;
import java.util.*;

class Main {
    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집 개수
        C = Integer.parseInt(st.nextToken()); // 공유기 개수
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        // 이진 탐색 (최소 거리: 1, 최대 거리: 가장 멀리 떨어진 두 집의 거리)
        int left = 1;
        int right = houses[N - 1] - houses[0];
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2; // (left + right) / 2 보다 안전한 방식
            if (canInstallRouters(mid)) { // 공유기 설치 가능 여부 체크
                result = mid; // 가능한 거리 저장
                left = mid + 1; // 거리 늘려보기
            } else {
                right = mid - 1; // 거리 줄이기
            }
        }

        System.out.println(result);
    }

    // 공유기 설치 가능 여부 확인 (true: 설치 가능, false: 설치 불가능)
    public static boolean canInstallRouters(int distance) {
        int count = 1; // 첫 번째 집에 공유기 설치
        int lastPosition = houses[0]; // 마지막 공유기 설치 위치

        for (int i = 1; i < N; i++) {
            if (houses[i] - lastPosition >= distance) { // 공유기 설치 가능하면
                count++;
                lastPosition = houses[i];
            }
        }
        return count >= C;
    }
}