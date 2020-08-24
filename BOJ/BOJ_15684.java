import java.io.*;
import java.util.*;

class Main {

    static int n, m, h, ladderCnt;
    static boolean exit;
    static boolean[][] ladder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        ladder = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ladder[r][c] = true;
        }

        for (int i = 0; i <= 3; i++) {
            ladderCnt = i;
            DFS(1, 0);

            if (exit) {
                System.out.println(ladderCnt);
                return;
            }
        }
        System.out.println(-1);
    }

    static void DFS(int height, int cnt) {
        if (exit)
            return;

        if (ladderCnt == cnt) {
            boolean flag = true;
            
            for (int i = 1; i <= n; i++) {
                int tmp = i;
                for (int j = 1; j <= h; j++) {
                    if (ladder[j][tmp - 1]) tmp--;
                    else if (ladder[j][tmp]) tmp++;
                }

                if (tmp != i) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                exit = true;

            return;
        }

        for (int i = height; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (!ladder[i][j - 1] && !ladder[i][j] && !ladder[i][j + 1]) {
                    ladder[i][j] = true;
                    DFS(i, cnt + 1);
                    ladder[i][j] = false;
                }
            }
        }
    }
}