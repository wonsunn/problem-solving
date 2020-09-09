import java.io.*;
import java.util.*;

class Main {

    static int r, c, t, x1, y1, x2, y2;
    static int[][] board, tmp;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[] acw = new int[]{1, 0, 3, 2};
    static int[] cw = new int[]{1, 2, 3, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[r][c];
        tmp = new int[r][c];

        boolean flag = true;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1 && flag) {
                    x1 = i; y1 = j;
                    flag = false;
                }
            }
        }
        x2 = x1 + 1; y2 = y1;

        while (t-- > 0) {
            for (int[] row : tmp)
                Arrays.fill(row, 0);

            dusting();

            for (int i = 0; i < r; i++)
                for (int j = 0; j < c; j++)
                    board[i][j] = tmp[i][j];

            cleaning();
        }

        int res = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] > 0)
                    res += board[i][j];
            }
        }

        System.out.println(res);
    }

    static void dusting() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] > 0) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k]; int ny = j + dy[k];

                        if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                        if ((nx == x1 && ny == y1) ||(nx == x2 && ny == y2)) continue;

                        cnt++;
                        tmp[nx][ny] += board[i][j] / 5;
                    }
                    tmp[i][j] += board[i][j] - cnt * (board[i][j] / 5);
                }
            }
        }
    }

    static void cleaning() {
        //반시계 방향
        int x = x1; int y = y1 + 1;
        board[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            while (true) {
                int nx = x + dx[acw[i]]; int ny = y + dy[acw[i]];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) break;
                if (nx == x1 && ny == y1) break;

                board[nx][ny] = tmp[x][y];
                x = nx; y = ny;
            }
        }

        //시계 방향
        x = x2; y = y2 + 1;
        board[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            while (true) {
                int nx = x + dx[cw[i]]; int ny = y + dy[cw[i]];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) break;
                if (nx == x2 && ny == y2) break;

                board[nx][ny] = tmp[x][y];
                x = nx; y = ny;
            }
        }
    }
}