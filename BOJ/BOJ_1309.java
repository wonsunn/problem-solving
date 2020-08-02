import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static final int MOD = 9901;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[3][n + 1];
        dp[0][1] = dp[1][1] = dp[2][1] = 1; // 0: 배치 x, 1: 왼쪽 배치, 2: 오른쪽 배치

        for (int i = 2; i <= n; i++) {
            dp[0][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1]) % MOD; // 배치 안하는 것은 어떤 경우든 가능
            dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD; // 전 줄에 배치가 안됐거나 오른쪽 배치될 때 왼쪽 배치 가능
            dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD; // 전 줄에 배치가 안됐거나 왼쪽 배치될 때 오른쪽 배치 가능
        }

        System.out.println((dp[0][n] + dp[1][n] + dp[2][n]) % MOD);
    }
}