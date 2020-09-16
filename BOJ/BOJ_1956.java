import java.io.*;
import java.util.*;

class Main {

    static final int MAX = 10000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken()); int e = Integer.parseInt(st.nextToken());
        int[][] board = new int[v + 1][v + 1];

        for (int[] row : board) Arrays.fill(row, MAX);
        for (int i = 1; i <= v; i++) board[i][i] = 0;
        for (int i = 0; i < e; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            board[tmp[0]][tmp[1]] = tmp[2];
        }

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }

        int res = MAX;
        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                res = Math.min(res, board[i][j] + board[j][i]);
            }
        }

        if (res == MAX) System.out.println(-1);
        else System.out.println(res);
    }
}