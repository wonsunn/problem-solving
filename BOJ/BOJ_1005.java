import java.io.*;
import java.util.*;

class Main {

    static int t, n, k, w;
    static boolean[][] order;
    static int[] delay, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            delay = new int[n + 1];
            dp = new int[n + 1];
            order = new boolean[n + 1][n + 1];

            Arrays.fill(dp, -1);

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++)
                delay[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                order[x][y] = true;
            }

            System.out.println(getTime(Integer.parseInt(br.readLine())));
        }
    }

    static int getTime(int dest) {
        if (dp[dest] != -1)
            return dp[dest];

        int time = 0;
        for (int i = 1; i <= n; i++) {
            if (order[i][dest]) {
                time = Math.max(time, getTime(i));
            }
        }

        return dp[dest] = time + delay[dest];
    }
}