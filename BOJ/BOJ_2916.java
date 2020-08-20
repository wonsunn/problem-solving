import java.io.*;
import java.util.*;

class Main {

    static int n, k;
    static int[] angle;
    static boolean[][] dp;
    static boolean[] answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        angle = new int[n];
        answer = new boolean[361];
        dp = new boolean[n][361];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            angle[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int res = Integer.parseInt(st.nextToken());

            solve(0, 0);
            if (answer[res]) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static void solve(int i, int sum) {
        if (i >= n || dp[i][sum])
            return;

        dp[i][sum] = true;
        answer[sum] = true;

        solve(i, (360 + sum + angle[i]) % 360);
        solve(i + 1, sum);
        solve(i, (360 + sum - angle[i]) % 360);
    }
}