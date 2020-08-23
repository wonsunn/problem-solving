import java.io.*;
import java.util.*;

class Main {

    static int m, n;
    static int[][] board, dp;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static long cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new int[m][n];
        dp = new int[m][n];
        for (int[] row: dp)
            Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(DFS(0, 0));
    }

    static int DFS(int x, int y) {
        if (x == m - 1 && y == n - 1) return 1;
        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            if (board[nx][ny] < board[x][y]) {
                dp[x][y] += DFS(nx, ny);
            }
        }

        return dp[x][y];
    }
}