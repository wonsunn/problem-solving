import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int t, n;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int k = 1; k <= t; k++) {
            n = Integer.parseInt(br.readLine());
            int mid = (n + 1) / 2;
            int sum = 0;
            board = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                String[] str = br.readLine().split("");
                for (int j = 1; j <= n; j++)
                    board[i][j] = Integer.parseInt(str[j - 1]);
            }

            for (int i = 1; i <= mid; i++) {
                for (int j = mid + 1 - i; j < mid + i; j++) {
                    sum += board[i][j];
                }
            }

            for (int i = mid - 1; i >= 1; i--) {
                for (int j = mid + 1 - i; j <= mid + i; j++) {
                    sum += board[n - i + 1][j];
                }
            }

            System.out.println("#" + k + " " + sum);
        }
    }
}