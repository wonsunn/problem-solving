import java.io.*;
import java.util.*;

class Main {

    static int n, cnt = 0;
    static int[] prime;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        prime = new int[n + 1];

        setPrime();
        for (int i = 2; i <= n; i++) {
            if (prime[i] != 0)
                primes.add(i);
        }

        int left = 0, right = 0, sum = 0;
        while (true) {
            if (sum >= n)
                sum -= primes.get(left++);
            else if (right == primes.size())
                break;
            else
                sum += primes.get(right++);

            if (sum == n)
                cnt++;
        }

        System.out.println(cnt);
    }

    static void setPrime() {
        for (int i = 2; i <= n; i++)
            prime[i] = i;

        for (int i = 2; i <= n; i++) {
            if (prime[i] == 0) continue;

            for (int j = i + i; j <= n; j += i)
                prime[j] = 0;
        }
    }
}