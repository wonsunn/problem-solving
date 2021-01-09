import java.io.*;
import java.util.*;

class Main {

    static int n;
    static int[] ans = new int[3];
    static int[][] paper;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, n);
        for (int a : ans) System.out.println(a);
    }

    static boolean isSame(int x, int y, int d) {
        int check = paper[x][y];
        for (int i = x; i < x + d; i++) {
            for (int j = y; j < y + d; j++) {
                if (check != paper[i][j])
                    return false;
            }
        }
        return true;
    }

    static void solve(int x, int y, int d) {
        if (isSame(x, y ,d)) {
            ans[paper[x][y] + 1]++;
            return;
        }

        int div = d / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                solve(x + div * i, y + div * j, div);
            }
        }
    }
}