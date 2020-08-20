import java.io.*;
import java.util.*;

class Main {

    static int n, m, res = 0;
    static int[] cnt = new int[3];
    static boolean[][] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            check[x][y] = true; check[y][x] = true;
        }

        solve(0, 0);
        System.out.println(res);
    }

    static void solve(int idx, int num) {
        if (idx == 3) {
            res++;
            return;
        }

        for (int i = num + 1; i <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < idx; j++) {
                if (check[cnt[j]][i]) {
                    flag= false;
                    break;
                }
            }
            if (flag) {
                cnt[idx] = i;
                solve(idx + 1, i);
            }
        }
    }
}