import java.io.*;
import java.util.*;

public class BOJ_10713 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] startCnt = new int[n+1];
        int[][] cost = new int[n][3];
        st = new StringTokenizer(br.readLine());

        int prev = -1;
        for (int i = 0; i < m; i++) {
            int dest = Integer.parseInt(st.nextToken());
            if (prev != -1) {
                startCnt[Math.min(prev, dest)]++;
                startCnt[Math.max(prev, dest)]--;
            }
            prev = dest;
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        long sum = 0, res = 0;
        for (int i = 1; i < n; i++) {
            sum += startCnt[i];
            res += Math.min(cost[i][0] * sum, cost[i][1] * sum + cost[i][2]);
        }

        System.out.println(res);
    }

}