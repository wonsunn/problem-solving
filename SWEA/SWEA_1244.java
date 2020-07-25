import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int t, n, max;
    static String board, tmp;
    static Boolean[][] visited = new Boolean[1000000][11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());
            board = st.nextToken();
            n = Integer.parseInt(st.nextToken());

            for (Boolean[] b: visited) Arrays.fill(b, false);

            max = 0;
            DFS(0);

            System.out.println("#" + i + " " + max);
        }
    }

    static void DFS(int cnt) {
        if (cnt == n) {
            max = Math.max(max, Integer.parseInt(board));
            return;
        }

        for (int i = 0; i < board.length(); i++) {
            for (int j = i; j < board.length(); j++) {
                if (board.charAt(i) == board.charAt(j)) continue;

                swap(i, j);
                if (!visited[Integer.parseInt(board)][cnt + 1]) {
                    DFS(cnt + 1);
                    visited[Integer.parseInt(board)][cnt + 1] = true;
                }
                swap(i, j);
            }
        }
    }

    static void swap(int a, int b) {
        String[] t;
        String temp;

        t = board.split("");

        temp = t[a];
        t[a] = t[b];
        t[b] = temp;

        board = "";
        for (String s : t) board += s;
    }
}