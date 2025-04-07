import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            PriorityQueue<Phone> pq = new PriorityQueue<>();
            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                pq.add(new Phone(br.readLine()));
            }

            if (n == 1) {
                sb.append("YES").append('\n');
                continue;
            }

            boolean isConsistent = true;
            Phone first = pq.poll();

            while (!pq.isEmpty()) {
                Phone second = pq.poll();
                if (isPrefixOf(first.phone, second.phone)) {
                    isConsistent = false;
                    break;
                }
                first = second;
            }

            sb.append(isConsistent ? "YES" : "NO").append('\n');
        }

        System.out.println(sb);
    }

    static boolean isPrefixOf(String prefix, String s) {
        int prefixLen = prefix.length();
        int sLen = s.length();

        if (prefixLen > sLen) return false;
        if (prefixLen == sLen) {
            return prefix.equals(s);
        }

        if (prefix.equals(s.substring(0, prefixLen))) return true;
        return false;
    }

    static class Phone implements Comparable<Phone> {
        String phone;

        public Phone(String phone) {
            this.phone = phone;
        }

        public int compareTo(Phone o) {
            return this.phone.compareTo(o.phone);
        }
    }

}