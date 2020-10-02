import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[] dx = new int[]{0, 0, 0, 1, -1};
    static int[] dy = new int[]{0, 1, -1, 0, 0};
    static int[][] board;
    static int[][] dir = new int[][]{{0, 0}, {4, 3}, {3, 4}, {1, 2}, {2, 1}};
    static boolean[][][] check;
    static Queue<State> q = new LinkedList<>();

    static private class State {
        int x, y, d, cnt;

        State(int x, int y, int d, int cnt) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n + 1][m + 1];
        check = new boolean[n + 1][m + 1][5];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        q.add(new State(tmp[0], tmp[1], tmp[2], 0));
        check[tmp[0]][tmp[1]][tmp[2]] = true;

        tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(BFS(tmp[0], tmp[1], tmp[2]));
    }

    static int BFS(int destX, int destY, int destD) {
        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.x == destX && cur.y == destY && cur.d == destD)
                return cur.cnt;

            // Turn dir(left, right)
            for (int i = 0; i < 2; i++) {
                int nd = dir[cur.d][i];

                if (isRight(cur.x, cur.y, nd)) {
                    if (check[cur.x][cur.y][nd]) continue;

                    q.add(new State(cur.x, cur.y, nd, cur.cnt + 1));
                    check[cur.x][cur.y][nd] = true;
                }
            }

            // Go k(1, 2, 3)
            for (int k = 1; k <= 3; k++) {
                int nx = cur.x + dx[cur.d] * k;
                int ny = cur.y + dy[cur.d] * k;

                if (isRight(nx, ny, cur.d)) {
                    if (check[nx][ny][cur.d]) continue;

                    q.add(new State(nx, ny, cur.d, cur.cnt + 1));
                    check[nx][ny][cur.d] = true;
                }
                else
                    break;
            }
        }

        return 0;
    }

    static boolean isRight(int nx, int ny, int d) {
        if (nx < 1 || nx > n || ny < 1 || ny > m || board[nx][ny] == 1) return false;
        else return true;
    }
}
