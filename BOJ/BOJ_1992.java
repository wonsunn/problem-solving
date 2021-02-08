import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static char[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        for (int i = 0; i < n; i++)
            board[i] = br.readLine().toCharArray();

        quadTree(0, 0, n);

        System.out.println(sb.toString());
    }

    static void quadTree(int x, int y, int p) {
        char val = board[x][y];
        boolean flag = true;
        for (int i = x; i < x + p; i++) {
            for (int j = y; j < y + p; j++) {
                if (board[i][j] != val) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
        }

        if (!flag) {
            sb.append('(');
            quadTree(x, y, p / 2);
            quadTree(x, y + p / 2, p / 2);
            quadTree(x + p / 2, y, p / 2);
            quadTree(x + p / 2, y + p / 2, p / 2);
            sb.append(')');
        }
        else {
            sb.append(val);
        }
    }
}