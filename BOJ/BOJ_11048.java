import java.io.*;
import java.util.*;

class Main {

    static int n, m, res = Integer.MIN_VALUE;
    static int[][] miro, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        miro = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++)
                miro[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++)
                dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + miro[i][j];
        }

        System.out.println(dp[n][m]);
    }
}
