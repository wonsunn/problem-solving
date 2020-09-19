import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][3];
        int[][] dp_min = new int[n][3]; int[][] dp_max = new int[n][3];
        int[] dy = new int[]{-1, 0, 1};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] row : dp_min)
            Arrays.fill(row, Integer.MAX_VALUE);

        for (int j = 0; j < 3; j++)
            dp_min[0][j] = dp_max[0][j] = board[0][j];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int ny = j + dy[k];
                    
                    if (ny < 0 || ny > 2) continue;
                    
                    dp_min[i][j] = Math.min(dp_min[i][j], dp_min[i - 1][ny] + board[i][j]);
                    dp_max[i][j] = Math.max(dp_max[i][j], dp_max[i - 1][ny] + board[i][j]);
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            min = Math.min(min, dp_min[n - 1][j]);
            max = Math.max(max, dp_max[n - 1][j]);
        }

        System.out.println(max + " " + min);
    }
}