import java.io.*;
import java.util.*;

public class Main {

    static final int N = 10000;
    static int t, n;
    static int[] prime = new int[N + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        setPrime();

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            int max = 0;
            for (int i = 2; i <= n / 2; i++) {
                if (prime[i] != 0 && prime[n - i] != 0) {
                    max = i;
                }
            }
            System.out.println(max + " " + (n - max));
        }
    }

    static void setPrime() {
        for (int i = 2; i <= N; i++) prime[i] = i;

        for (int i = 2; i <= N; i++) {
            if (prime[i] == 0) continue;
            for (int j = i + i; j <= N; j += i) {
                prime[j] = 0;
            }
        }
    }
}