import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++)
            board[i] = br.readLine().toCharArray();

        q.add(new Node(0, 0, 1));
        visited[0][0] = true;
        System.out.println(BFS());
    }

    static int BFS() {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == n - 1 && cur.y == m - 1)
                return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny] || board[nx][ny] == '0') continue;

                q.add(new Node(nx, ny, cur.cnt + 1));
                visited[nx][ny] = true;
            }
        }
        return 0;
    }
}

class Node {
    int x, y, cnt;

    Node (int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}