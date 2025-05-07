import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // N 이하의 모든 소수 리스트 구하기 (에라토스테네스의 체)
        List<Integer> primes = getPrimesFaster(N);

        // 투 포인터로 연속된 소수 합 찾기
        int left = 0, right = 0;
        int sum = 0, count = 0;

        while (true) {
            if (sum >= N) {
                if (sum == N) count++;
                sum -= primes.get(left);
                left++;
            } else {
                if (right == primes.size()) break;
                sum += primes.get(right);
                right++;
            }
        }

        System.out.println(count);
    }

    public static List<Integer> getPrimes(int N) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isNotPrime = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {
            if (!isNotPrime[i]) { // i가 소수인 경우
                primes.add(i);
                for (int j = i * 2; j <= N; j += i) { // i의 배수는 소수가 아님
                    isNotPrime[j] = true;
                }
            }
        }

        return primes;
    }

    public static List<Integer> getPrimesFaster(int N) {
        List<Integer> primes = new ArrayList<>();
        boolean[] isNotPrime = new boolean[N + 1];

        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (!isNotPrime[i]) { // i가 소수인 경우
                for (int j = i * i; j <= N; j += i) { // i의 배수는 소수가 아님
                    isNotPrime[j] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!isNotPrime[i]) primes.add(i);
        }

        return primes;
    }
}