import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] chess = new String[N];
        for (int i = 0; i < N; i++) {
            chess[i] = br.readLine();
        }
        int min = Integer.MAX_VALUE;
        int resultA = 0;
        int resultB = 0;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                for (int k = i; k < 8 + i; k++) {
                    for (int l = j; l < 8 + j; l++) {
                        if ((k % 2 == 0 && l % 2 == 0) || (k % 2 != 0 && l % 2 != 0)) {
                            if (chess[k].charAt(l) != 'W') {
                                resultA++;
                            }
                            if (chess[k].charAt(l) != 'B') {
                                resultB++;
                            }
                        } else {
                            if (chess[k].charAt(l) != 'B') {
                                resultA++;
                            }
                            if (chess[k].charAt(l) != 'W') {
                                resultB++;
                            }
                        }
                    }
                }
                if (min > resultA) {
                    min = resultA;
                }
                if (min > resultB) {
                    min = resultB;
                }
                resultA = 0;
                resultB = 0;
            }
        }
        System.out.println(min);
    }
}