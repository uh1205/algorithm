import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기 개수
        int[] houses = new int[N];

        // 집 좌표 입력
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 집 좌표 정렬
        Arrays.sort(houses);

        // 이진 탐색 범위 설정
        int left = 1; // 최소 거리
        int right = houses[N - 1] - houses[0]; // 최대 거리
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 현재 거리 후보
            int count = 1; // 첫 번째 집에 공유기 설치
            int lastPosition = houses[0]; // 마지막으로 공유기를 설치한 위치

            // 현재 mid 거리로 공유기 설치 가능 여부 확인
            for (int i = 1; i < N; i++) {
                if (houses[i] - lastPosition >= mid) { // 현재 거리보다 크거나 같으면 설치 가능
                    count++;
                    lastPosition = houses[i];
                }
            }

            if (count >= C) { // 공유기 개수를 충족하면 거리를 늘려본다.
                result = mid;
                left = mid + 1;
            } else { // 공유기 개수가 부족하면 거리를 줄여야 한다.
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}