import java.io.*;
import java.util.*;

class Main {

    static int h, w;
    static int[][] board, check;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    static Queue<Node> q = new LinkedList<>();
    static Queue<Node> inner = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        board = new int[h][w];
        check = new int[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < w; j++) {
                if (tmp.charAt(j) == '.') {
                    board[i][j] = 0;
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                }
                else {
                    board[i][j] = tmp.charAt(j) - '0';
                }
            }
        }

        System.out.println(BFS());;
    }

    static int BFS() {
        int res = 0;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
                if (visited[nx][ny]) continue;

                if (board[nx][ny] > 0)
                    check[nx][ny] += 1;

                if (check[nx][ny] >= board[nx][ny]) {
                    inner.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            if (q.isEmpty() && !inner.isEmpty()) {
                res++;

                while (!inner.isEmpty()) {
                    Node inner_cur = inner.poll();
                    q.add(new Node(inner_cur.x, inner_cur.y));
                }
            }
        }

        return res;
    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}