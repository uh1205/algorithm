import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] times = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times); // 오름차순 정렬

        int sum = 0;
        int total = 0;

        for (int i = 0; i < N; i++) {
            sum += times[i]; // 현재 사람까지 걸리는 누적 시간
            total += sum;    // 전체 대기 시간에 더함
        }

        System.out.println(total);
    }
}