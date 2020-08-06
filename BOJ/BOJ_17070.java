import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, cnt = 0;
    static int[][] board;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};

    public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        board = new int[n + 2][n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(1, 2, 0);

        System.out.println(cnt);
    }

    static void DFS(int x, int y, int mode) {
        if ((x == n && y == n)) {
            cnt++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            if ((i == 0 && mode == 1) || (i == 1 && mode == 0)) continue;

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > n || ny > n || board[nx][ny] == 1) continue;
            if (i == 2 && (board[x + 1][y] == 1 || board[x][y + 1] == 1)) continue;

            DFS(nx, ny, i);
        }
    }
}