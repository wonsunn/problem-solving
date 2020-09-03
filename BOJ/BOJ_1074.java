import java.io.*;
import java.util.*;

class Main {

    static int n, r, c, cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        DFS(1 << n, 0, 0);
    }

    static void DFS(int size, int x, int y) {
        if (size == 2) {
            if (x == r && y == c) {
                System.out.println(cnt);
                return;
            }
            cnt++;

            if (x == r && y + 1 == c) {
                System.out.println(cnt);
                return;
            }
            cnt++;

            if (x + 1 == r && y == c) {
                System.out.println(cnt);
                return;
            }
            cnt++;

            if (x + 1 == r && y + 1 == c) {
                System.out.println(cnt);
                return;
            }
            cnt++;

            return;
        }

        DFS(size / 2, x, y);
        DFS(size / 2, x, y + size / 2);
        DFS(size / 2, x + size / 2, y);
        DFS(size / 2, x + size / 2, y + size / 2);
    }
}