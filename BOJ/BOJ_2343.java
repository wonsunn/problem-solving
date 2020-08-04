import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, left, right = 0, mid, ans;
    static int[] lesson;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        lesson = new int[n];

        left = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lesson[i] = Integer.parseInt(st.nextToken());
            right += lesson[i];
        }

        while (left <= right) {
            mid = (left + right) / 2;

            if (check(mid)) {
                right = mid - 1;
                ans = mid;
            }
            else left = mid + 1;
        }
        System.out.println(ans);
    }

    static boolean check(int mid) {
        int cnt = 1, tmp = 0;

        for (int i = 0; i < n; i++) {
            if (lesson[i] > mid) {
                left = mid + 1;
                return false;
            }

            tmp += lesson[i];
            if (tmp > mid) {
                cnt++;
                tmp = lesson[i];
            }
        }
        return cnt <= m;
    }
}
