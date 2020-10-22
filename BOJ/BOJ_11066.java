import java.io.*;
import java.util.*;

class Main {

    static final int max = Integer.MAX_VALUE;
    static int t, n;
    static int[][] dp;
    static int[] cSum, arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            cSum = new int[n + 1];
            arr = new int[n + 1];
            dp = new int[n + 1][n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                cSum[i] = cSum[i - 1] + arr[i];
            }

            for (int j = 2; j <= n; j++) {
                for (int i = j - 1; i > 0; i--) {
                    dp[i][j] = max;
                    for (int k = i; k < j; k++) 
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    
                    dp[i][j] += cSum[j] - cSum[i - 1];
                }
            }

            System.out.println(dp[1][n]);
        }
    }
}