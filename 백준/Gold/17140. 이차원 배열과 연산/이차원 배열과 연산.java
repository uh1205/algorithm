import java.io.*;
import java.util.*;

public class Main {
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        A = new int[3][3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time <= 100) {
            int rowLen = A.length;
            int colLen = A[0].length;

            if (rowLen > r && colLen > c && A[r][c] == k) {
                break;
            }

            if (rowLen >= colLen) {
                R();
            } else {
                C();
            }
            time++;
        }

        System.out.println(time > 100 ? -1 : time);
    }

    static void R() {
        int rowLen = A.length;
        int maxColLen = 0;

        List<Integer>[] newTable = new ArrayList[rowLen];
        for (int i = 0; i < rowLen; i++) {
            newTable[i] = new ArrayList<>();
        }

        for (int r = 0; r < rowLen; r++) {
            Map<Integer, Integer> numToCount = new HashMap<>();
            for (int c = 0; c < A[r].length; c++) {
                if (A[r][c] == 0) {
                    continue; // 0은 무시
                }
                numToCount.put(A[r][c], numToCount.getOrDefault(A[r][c], 0) + 1);
            }

            List<Pair> pairs = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
                pairs.add(new Pair(entry.getKey(), entry.getValue()));
            }

            pairs.sort(null);

            for (Pair pair : pairs) {
                newTable[r].add(pair.num);
                if (newTable[r].size() == 100) {
                    break;
                }
                newTable[r].add(pair.count);
                if (newTable[r].size() == 100) {
                    break;
                }
            }

            maxColLen = Math.max(maxColLen, newTable[r].size());
        }

        A = new int[rowLen][maxColLen];
        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < newTable[r].size(); c++) {
                A[r][c] = newTable[r].get(c);
            }
        }
    }

    static void C() {
        int maxRowLen = 0;
        int colLen = A[0].length;

        List<Integer>[] newTable = new ArrayList[colLen];
        for (int i = 0; i < colLen; i++) {
            newTable[i] = new ArrayList<>();
        }

        for (int c = 0; c < colLen; c++) {
            Map<Integer, Integer> numToCount = new HashMap<>();
            for (int[] row : A) {
                if (row[c] == 0) {
                    continue; // 0은 무시
                }
                numToCount.put(row[c], numToCount.getOrDefault(row[c], 0) + 1);
            }

            List<Pair> pairs = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : numToCount.entrySet()) {
                pairs.add(new Pair(entry.getKey(), entry.getValue()));
            }

            pairs.sort(null);

            for (Pair pair : pairs) {
                newTable[c].add(pair.num);
                if (newTable[c].size() == 100) {
                    break;
                }
                newTable[c].add(pair.count);
                if (newTable[c].size() == 100) {
                    break;
                }
            }

            maxRowLen = Math.max(maxRowLen, newTable[c].size());
        }

        A = new int[maxRowLen][colLen];
        for (int c = 0; c < colLen; c++) {
            for (int r = 0; r < newTable[c].size(); r++) {
                A[r][c] = newTable[c].get(r);
            }
        }
    }

    static class Pair implements Comparable<Pair> {
        int num, count;

        public Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count != o.count) {
                return Integer.compare(this.count, o.count);
            }
            return Integer.compare(this.num, o.num);
        }
    }
}
