import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); int k = Integer.parseInt(st.nextToken());
        int[][] board = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            board[tmp[0]][tmp[1]] = 1;
        }

        for (int p = 1; p <= n; p++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (board[i][p] == 1 && board[p][j] == 1)
                        board[i][j] = 1;
                }
            }
        }

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if (board[tmp[0]][tmp[1]] == 1) System.out.println(-1);
            else if (board[tmp[1]][tmp[0]] == 1) System.out.println(1);
            else System.out.println(0);
        }
    }
}