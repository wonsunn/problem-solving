import java.io.*;
import java.util.*;

class Main {

    static int n;
    static int[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n + 1];
        B = new int[n + 1];
        dp = new int[n + 1][n + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) B[i] = Integer.parseInt(st.nextToken());

        System.out.println(DFS(1, 1));
    }

    static int DFS(int l, int r) {
        if (l > n || r > n) return 0;
        if (dp[l][r] != -1) return dp[l][r];

        dp[l][r] = 0;
        dp[l][r] = Math.max(DFS(l + 1, r), DFS(l + 1, r + 1));
        if (A[l] > B[r])
            dp[l][r] = Math.max(dp[l][r], DFS(l, r + 1) + B[r]);

        return dp[l][r];
    }
}