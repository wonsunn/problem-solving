import java.io.*;
import java.util.*;

class Main {

    static int n;
    static int[] miro, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        miro = new int[n + 1];
        dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            miro[i] = Integer.parseInt(st.nextToken());

        dp[1] = 1;
        for (int i = 1; i < n; i++) {
            if (dp[i] == 0)
                continue;

            for (int j = i + 1; j <= i + miro[i] && j <= n; j++) {
                if (dp[j] == 0)
                    dp[j] = dp[i] + 1;
            }
        }

        System.out.println(dp[n] == 0 ? -1 : dp[n] - 1);
    }
}