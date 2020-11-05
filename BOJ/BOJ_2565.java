import java.io.*;
import java.util.*;

class Main {

    private static class Loc {
        int a, b;

        Loc (int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Loc[] loc = new Loc[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            loc[i] = new Loc(a, b);
        }
        Arrays.sort(loc, (l1, l2) -> l1.a - l2.a);

        int max = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (loc[i].b > loc[j].b)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);
    }
}