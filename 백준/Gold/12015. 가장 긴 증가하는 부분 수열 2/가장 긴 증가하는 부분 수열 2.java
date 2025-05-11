import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> lis = new ArrayList<>();

        for (int num : A) {
            if (lis.isEmpty() || lis.get(lis.size() - 1) < num) {
                lis.add(num);
            } else {
                int idx = Collections.binarySearch(lis, num);
                if (idx < 0) {
                    idx = -(idx + 1); // 삽입 위치 반환
                }
                lis.set(idx, num); // 더 작은 수로 대체
            }
        }

        System.out.println(lis.size());
    }
}