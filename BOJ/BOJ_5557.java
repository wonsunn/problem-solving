import java.io.*;
import java.util.*;

class Main {

    static int n;
    static int[] number;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        number = new int[n];
        dp = new long[n][21];
        for (long[] row : dp)
            Arrays.fill(row, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            number[i] = Integer.parseInt(st.nextToken());

        System.out.println(solve(1, number[0]));
    }

    static long solve(int level, int num) {
        if (level >= n || num < 0 || num > 20) return 0;
        if (dp[level][num] != -1) return dp[level][num];
        if (level == n - 1 && num == number[n - 1]) return 1;

        dp[level][num] = solve(level + 1, num + number[level]) + solve(level + 1, num - number[level]);

        return dp[level][num];
    }
}