import java.io.*;
import java.util.*;

public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
            String[] data = br.readLine().split(" ");

            // A, B, C는 최대 10의 12승이므로 long 타입에 저장해야함
            long a = Long.parseLong(data[0]);
            long b = Long.parseLong(data[1]);
            long c = Long.parseLong(data[2]);

            System.out.println(a + b + c);
        }
    }