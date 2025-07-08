import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        // 예외: 센서 수보다 집중국 수가 많거나 같으면, 각 센서에 하나씩 배정 → 거리 0
        if (K >= N) {
            System.out.println(0);
            return;
        }

        Arrays.sort(sensors);

        // 인접 센서 간 거리 계산
        int[] diff = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = sensors[i + 1] - sensors[i];
        }

        // 거리 내림차순 정렬
        Arrays.sort(diff);

        // 가장 큰 거리 K-1개를 제거 → 나머지 거리 합이 최소 수신 길이
        int result = 0;
        for (int i = 0; i < N - K; i++) {
            result += diff[i];
        }

        System.out.println(result);
    }
}