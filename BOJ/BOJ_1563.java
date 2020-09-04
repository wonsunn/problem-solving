import java.io.*;
import java.util.*;

class Main {

    static final int MOD = 1000000;
    static int n;
    static int[][][] dp = new int[1000][2][3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int[][] arr : dp) {
            for (int[] row : arr) {
                Arrays.fill(row, -1);
            }
        }

        System.out.println(solve(0, 0, 0));
    }

    static int solve (int day, int l, int a) {
        if (l >= 2 || a >= 3)
            return 0;

        if (day == n)
            return 1;

        if (dp[day][l][a] != -1)
            return dp[day][l][a];

        return dp[day][l][a] = (solve(day + 1, l, 0) + solve(day + 1, l + 1, 0) + solve(day + 1, l, a + 1)) % MOD;
    }
}