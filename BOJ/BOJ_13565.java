import java.io.*;
import java.util.*;

public class BOJ_13565 {

    static int n, m;
    static char[][] board;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++)
            board[i] = br.readLine().toCharArray();

        for (int i = 0; i < m; i++) {
            if (board[0][i] == '0') {
                q.add(new int[]{0,i});
                visited[0][i] = true;
            }
        }

        System.out.println(BFS() ? "YES" : "NO");
    }

    static boolean BFS() {
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == n-1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || board[nx][ny] == '1') continue;

                q.add(new int[]{nx,ny});
                visited[nx][ny] = true;
            }
        }

        return false;
    }
}
