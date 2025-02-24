import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double s1 = 0; // 학점의 총합
        double s2 = 0; // (학점 × 과목평점)의 합
        String s;
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            st.nextToken(); // 과목명
            double credit = Double.parseDouble(st.nextToken()); // 학점
            String grade = st.nextToken(); // 등급
            switch (grade) {
                case "A+":
                    s1 += credit;
                    s2 += credit * 4.5;
                    break;
                case "A0":
                    s1 += credit;
                    s2 += credit * 4.0;
                    break;
                case "B+":
                    s1 += credit;
                    s2 += credit * 3.5;
                    break;
                case "B0":
                    s1 += credit;
                    s2 += credit * 3.0;
                    break;
                case "C+":
                    s1 += credit;
                    s2 += credit * 2.5;
                    break;
                case "C0":
                    s1 += credit;
                    s2 += credit * 2.0;
                    break;
                case "D+":
                    s1 += credit;
                    s2 += credit * 1.5;
                    break;
                case "D0":
                    s1 += credit;
                    s2 += credit;
                    break;
                case "F":
                    s1 += credit;
                    break;
                case "P":
                    break;
            }
        }
        System.out.println(s2 / s1);
    }
}
