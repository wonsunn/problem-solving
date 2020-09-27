import java.io.*;
import java.util.*;

class Main {

    static int t, w, ans = Integer.MIN_VALUE;
    static int[] locate;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        locate = new int[t + 1];
        dp = new int[3][t + 1][w + 2]; // dp[나무 위치][시간][이동 횟수]

        for (int i = 1; i <= t; i++)
            locate[i] = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            for (int j = 1; j <= w + 1; j++) {
                if (locate[i] == 1) {
                    dp[1][i][j] = Math.max(dp[1][i - 1][j] + 1, dp[2][i - 1][j - 1] + 1);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1], dp[2][i - 1][j]);
                }
                else {
                    if (i == 1 && j == 1) continue; // 2번 자두를 먹기 위함

                    dp[1][i][j] = Math.max(dp[1][i - 1][j], dp[2][i - 1][j - 1]);
                    dp[2][i][j] = Math.max(dp[1][i - 1][j - 1] + 1, dp[2][i - 1][j] + 1);
                }
            }
        }

        for (int i = 1; i <= w + 1; i++)
            ans = Math.max(ans, Math.max(dp[1][t][i], dp[2][t][i]));

        System.out.println(ans);
    }
}