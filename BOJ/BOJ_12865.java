import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            int[] bags = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j <= k; j++) {
                // 현재 배낭을 담지 못할 때
                if (bags[0] > j) dp[i][j] = dp[i - 1][j];
                // 담을 수 있을 때
                else dp[i][j] = Math.max(dp[i - 1][j - bags[0]] + bags[1], dp[i - 1][j]);
            }
        }

        System.out.println(dp[n][k]);
    }
}