import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static long m, left, right, mid, ans;
    static long[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = new long[n];

        for (int i = 0; i < n; i++) time[i] = Integer.parseInt(br.readLine());
        Arrays.sort(time);

        left = 0; right = m * time[n - 1];

        while (left <= right) {
            mid = (left + right) / 2;
            long res = 0;

            for (long t : time)
                res += (mid / t);

            if (res < m) left = mid + 1;
            else {
                right = mid - 1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }
}
