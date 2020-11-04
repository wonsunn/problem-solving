import java.io.*;
import java.util.*;

class Main {

    static final int MAX = 123456 * 2;
    static int[] prime = new int[MAX + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        getPrimeNumber();

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            int cnt = 0;
            for (int i = n + 1; i <= 2 * n; i++) {
                if (prime[i] != 0)
                    cnt++;
            }

            System.out.println(cnt);
        }
    }

    static void getPrimeNumber() {
        for (int i = 1; i <= MAX; i++)
            prime[i] = i;

        for (int i = 2; i <= MAX; i++) {
            if (prime[i] == 0) continue;

            for (int j = i + i; j <= MAX; j += i) {
                prime[j] = 0;
            }
        }
    }
}