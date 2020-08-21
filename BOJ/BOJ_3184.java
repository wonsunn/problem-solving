import java.io.*;
import java.util.*;

class Main {

    static int r, c, v_total = 0, o_total = 0;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++)
            board[i] = br.readLine().toCharArray();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] != '#' && !visited[i][j]) {
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                    BFS();
                }
            }
        }

        System.out.println(o_total + " " + v_total);
    }

    static void BFS() {
        int v_num = 0; int o_num = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            if (board[cur.x][cur.y] == 'v')
                v_num++;
            else if (board[cur.x][cur.y] == 'o')
                o_num++;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (visited[nx][ny] || board[nx][ny] == '#') continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }

        if (o_num > v_num)
            o_total += o_num;
        else
            v_total += v_num;

    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}