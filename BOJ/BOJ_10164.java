import java.io.*;
import java.util.*;

class Main {

    static int n, m, k, kx, ky;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];

        if (k == 0) {
            kx = 0; ky = 0;
        }
        else {
            if (k % m == 0) {
                kx = k / m; ky = m;
            }
            else {
                kx = k / m + 1; ky = k % m;
            }
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;

                if ((i < kx && j > ky) || (i > kx && j < ky))
                    dp[i][j] = 0;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        System.out.println(dp[n][m]);
    }
}