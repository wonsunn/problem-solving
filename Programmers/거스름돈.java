import java.util.*;
/*
dp[x][y] : x번 째까지의 동전으로 y원 만드는 개수
*/
class Solution {
     public int solution(int n, int[] money) {
        int[][] dp = new int[money.length][n + 1];
        Arrays.sort(money);
        
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i % money[0] == 0) 
                dp[0][i] = 1;
        }
        
        /*
        금액이 가지고 있는 동전 보다 크거나 같아질 때,
        -> 그 전의 동전을 가지고 해당 금액을 만든 개수 + 현재 동전을 사용하고 남은 차액을 만든 개수
        */
        for (int m = 1; m < money.length; m++) {
            for (int i = 0; i <= n; i++) {
                if (i >= money[m]) 
                    dp[m][i] = (dp[m - 1][i] + dp[m][i - money[m]]) % 1000000007;
                else
                    dp[m][i] = dp[m - 1][i];
            }
        }

        return dp[money.length - 1][n];
    }
}