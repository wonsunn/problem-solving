import java.io.*;
import java.util.*;

class Main {

    static int n, min = Integer.MAX_VALUE;
    static int[][] taste;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        taste = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++)
                taste[i][j] = Integer.parseInt(st.nextToken());
        }

        DFS(0, 1, 0);

        System.out.println(min);
    }

    static void DFS(int idx, int t1, int t2) {
        if (idx == n) {
            if (t2 != 0)
                min = Math.min(min, Math.abs(t1 - t2));

            return;
        }
        
        DFS(idx + 1, t1 * taste[idx][0], t2 + taste[idx][1]);
        DFS(idx + 1, t1, t2);
    }
}
