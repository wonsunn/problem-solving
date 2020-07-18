import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        for (int i = 1; i <= (int) Math.sqrt(n); i++) dp[i * i] = 1;

        for (int i = 2; i <= n; i++) {
            if (dp[i] == 1) continue;

            int res = INF;
            for (int j = (int) Math.sqrt(i); j > 0; j--) {
                int cnt = 1 + dp[i - (j * j)];
                res = Math.min(res, cnt);
            }
            dp[i] = res;
        }
        System.out.println(dp[n]);
    }
}