import java.io.*;
import java.util.*;

class Main {

    static int k, w, h;
    static int[][] board;
    static boolean[][][] visited;

    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] hx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

    private static class Info {
        int x, y, knight, cnt;

        Info (int x, int y, int knight, int cnt) {
            this.x = x;
            this.y = y;
            this.knight = knight;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        visited = new boolean[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(BFS());
    }

    static int BFS() {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            int curX = q.peek().x;
            int curY = q.peek().y;
            int knight = q.peek().knight;
            int cnt = q.peek().cnt;

            q.poll();

            if (curX == h - 1 && curY == w - 1) {
                return cnt;
            }

            if (knight < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = curX + hx[i];
                    int ny = curY + hy[i];

                    if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                        if (board[nx][ny] == 0 && !visited[nx][ny][knight + 1]) {
                            visited[nx][ny][knight + 1] = true;
                            q.add(new Info(nx, ny, knight + 1, cnt + 1));
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                    if (board[nx][ny] == 0 && !visited[nx][ny][knight]) {
                        visited[nx][ny][knight] = true;
                        q.add(new Info(nx, ny, knight, cnt + 1));
                    }
                }
            }
        }

        return -1;
    }
}