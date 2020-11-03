import java.io.*;
import java.util.*;

class Main {

    static final int N = 20;
    static int[][] board = new int[N][N];
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (board[i][j] != 0) {
                    for (int k = 0; k < 8; k++) {
                        int val = check(board[i][j], i, j, k);

                        int px = i - dx[k];
                        int py = j - dy[k];

                        if (px >= 1 && px < N && py >= 1 && py < N && board[px][py] == board[i][j])
                            continue;

                        if (val == 5) {
                            System.out.println(board[i][j]);
                            if (k == 2 || k == 3 || k == 4 || k == 5)
                                System.out.print(i + " " + j);
                            else
                                System.out.print((i + dx[k] * 4) + " " + (j + dy[k] * 4));

                            return;
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }

    static int check(int color, int x, int y, int k) {
        int cnt = 1;
        while (true) {
            x += dx[k];
            y += dy[k];

            if (x < 1 || x >= N || y < 1 || y >= N || board[x][y] != color) {
                return cnt;
            }

            cnt++;
        }
    }
}
