import java.io.*;
import java.util.*;

class Main {
    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        // 빠른 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = readInt();
        C = readInt();
        houses = new int[N];

        // 집 좌표 입력 & 정렬
        for (int i = 0; i < N; i++) {
            houses[i] = readInt();
        }
        Arrays.sort(houses);

        // 이진 탐색 (최소 거리: 1, 최대 거리: 가장 멀리 떨어진 두 집의 거리)
        int left = 1, right = houses[N - 1] - houses[0];
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

    // 빠른 정수 입력 (System.in.read() 활용)
    public static int readInt() throws IOException {
        int value = 0, sign = 1;
        int c = System.in.read();
        while (c <= ' ') c = System.in.read(); // 공백 문자 건너뛰기
        if (c == '-') { sign = -1; c = System.in.read(); } // 음수 처리

        while (c >= '0' && c <= '9') { // 숫자 변환
            value = value * 10 + (c - '0');
            c = System.in.read();
        }
        return value * sign;
    }
}