import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<long[]> points = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;

        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long a = line[i][0], b = line[i][1], e = line[i][2];
                long c = line[j][0], d = line[j][1], f = line[j][2];

                // 분모
                long parent = a * d - b * c;

                if (parent == 0) {
                    continue;
                }

                // 분자
                long childX = b * f - e * d;
                long childY = e * c - a * f;

                if (childX % parent == 0 && childY % parent == 0) {
                    long x = childX / parent;
                    long y = childY / parent;

                    points.add(new long[]{x, y});

                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        int n = (int) (maxY - minY + 1);
        int m = (int) (maxX - minX + 1);

        char[][] grid = new char[n][m];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }

        for (long[] p : points) {
            int r = (int) (maxY - p[1]);
            int c = (int) (p[0] - minX);
            grid[r][c] = '*';
        }

        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            ans[i] = String.valueOf(grid[i]);
        }

        return ans;
    }
}
