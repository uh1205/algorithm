import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        for (char alp = 'a'; alp <= 'z'; alp++) {
            bw.write(str.indexOf(alp) + " ");
        }
        bw.flush();
    }
}