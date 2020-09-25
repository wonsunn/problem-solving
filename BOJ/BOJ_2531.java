import java.io.*;
import java.util.*;

class Main {

    static int n, d, k, c;
    static int[] check, belt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());

        check = new int[d + 1];
        belt = new int[n];
        for (int i = 0; i < n; i++)
            belt[i] = Integer.parseInt(br.readLine());

        int max = 0, cnt = 0;
        for (int i = 0; i < k; i++) {
            if (check[belt[i]] == 0) cnt++;
            check[belt[i]]++;
        }

        max = cnt;

        for (int i = 1; i < n; i++) {
            if (max <= cnt) {
                if (check[c] == 0) max = cnt + 1; // 현재 쿠폰이 저장되어있지 않으면 +1
                else max = cnt;
            }

            check[belt[i - 1]]--;
            if (check[belt[i - 1]] == 0) cnt--;

            if (check[belt[(i + k - 1) % n]] == 0) cnt++;
            check[belt[(i + k - 1) % n]]++;
        }

        System.out.println(max);
    }
}
