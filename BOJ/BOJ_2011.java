import java.io.*;
import java.util.*;

class Main {
    
    static final int MOD = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pw = br.readLine();
        
        int len = pw.length();
        int[] dp = new int[len + 1];
        int[] arr = new int[len + 1];
        
        for (int i = 0; i < len; i++)
            arr[i + 1] = pw.charAt(i) - '0';

        if (len == 1 && arr[1] == 0) {
            System.out.println(0);
            return;
        }
        
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            if (arr[i] >= 1 && arr[i] <= 9)
                dp[i] = (dp[i - 1] + dp[i]) % MOD;
            
            if (i == 1) continue;
            
            int tmp = arr[i - 1] * 10 + arr[i];
            if (tmp >= 10 && tmp <= 26)
                dp[i] = (dp[i - 2] + dp[i]) % MOD;
        }

        System.out.println(dp[len]);
    }
}