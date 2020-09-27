import java.io.*;
import java.util.*;

class Main {

    static int n, m, ans = Integer.MAX_VALUE;
    static int[] memory, cost;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memory = new int[n + 1];
        cost = new int[n+ 1];
        dp = new int[n + 1][10001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            memory[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 10000; j++) {
                if (cost[i] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j - cost[i]] + memory[i], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];

                if (dp[i][j] >= m)
                    ans = Math.min(ans, j);
            }
        }

        System.out.println(ans);
    }
}