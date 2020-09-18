import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String[] tmp = br.readLine().split("");
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(tmp[j - 1]);
            }
        }
        
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        System.out.println(res * res);
    }
}