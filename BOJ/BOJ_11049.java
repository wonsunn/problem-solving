import java.io.*;
import java.util.*;

class Main {

    static int n, res = Integer.MAX_VALUE;
    static int[][] matrix, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        matrix = new int[n][2];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
         
                for (int k = i; k < j; k++) {
                    res = dp[i][k] + dp[k + 1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]);
                    if (dp[i][j] == 0 || dp[i][j] > res)
                        dp[i][j] = res;
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}