import java.io.*;
import java.util.*;

class Main {

    static final int MOD = 1000000009;
    static int n, t;
    static long[] dp = new long[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < 1000001; i++) {
            dp[i] += dp[i - 3] % MOD;
            dp[i] += dp[i - 2] % MOD;
            dp[i] += dp[i - 1] % MOD;
        }

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            System.out.println(dp[n] % MOD);
        }
    }
}