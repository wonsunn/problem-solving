import java.io.*;
import java.util.*;

class Main {

    static int n, l, r;
    static int[][] board;
    static boolean[][] union;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static Queue<Node> q;
    static List<Node> list = new ArrayList<>();

    private static class Node {
        int x, y;

        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        board = new int[n + 1][n + 1];
        union = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        while (true) {
            for (boolean[] row : union)
                Arrays.fill(row, false);

            boolean flag = false;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (!union[i][j]) {
                        int sum = openWall(i, j);

                        if (list.size() > 1) flag = true;

                        for (Node cur : list)
                            board[cur.x][cur.y] = sum / list.size();
                    }
                }
            }

            if (!flag) {
                System.out.println(cnt);
                break;
            }

            cnt++;
        }
    }

    static int openWall(int x, int y) {
        list.clear();
        q = new LinkedList<>();

        q.add(new Node(x, y));
        union[x][y] = true;

        int sum = 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            list.add(new Node(cur.x, cur.y));
            sum += board[cur.x][cur.y];

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n || union[nx][ny])
                    continue;

                int diff = Math.abs(board[cur.x][cur.y] - board[nx][ny]);
                if (diff >= l && diff <= r) {
                    q.add(new Node(nx, ny));
                    union[nx][ny] = true;
                }
            }
        }

        return sum;
    }
}
