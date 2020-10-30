import java.io.*;
import java.util.*;

class Main {

    static int n, c, ans = 0;
    static int[] loc;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        loc = new int[n];
        for (int i = 0; i < n; i++)
            loc[i] = Integer.parseInt(br.readLine());
        Arrays.sort(loc);

        int s = 1, e = loc[n - 1] - loc[0];
        while (s <= e) {
            int mid = (s + e) / 2;

            if (isPossible(mid)) {
                ans = mid;
                s = mid + 1;
            }
            else {
                e = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static boolean isPossible(int dist) {
        int prev = loc[0], cnt = 1;
        for (int i = 1; i < n; i++) {
            if (loc[i] - prev >= dist) {
                cnt++;
                prev = loc[i];
            }
        }

        if (cnt >= c) return true;
        else return false;
    }
}

