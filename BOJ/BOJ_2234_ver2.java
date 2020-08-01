import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, roomCnt = 0, maxArea = 0, maxAreaPlus = 0;
    static int[][] board;
    static boolean[][] visited; // 방의 번호를 저장하면서 visit 처리
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j< n; j++) {
                if (!visited[i][j]) {
                    roomCnt++;
                    maxArea = Math.max(maxArea, BFS(i, j));
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int w = 1; w <= 8; w <<= 1) {
                    if ((board[i][j] & w) == w) {
                        for (boolean[] row : visited) Arrays.fill(row, false);
                        board[i][j] -= w;
                        maxAreaPlus = Math.max(maxAreaPlus, BFS(i, j));
                        board[i][j] += w;
                    }
                }
            }
        }

        System.out.println(roomCnt);
        System.out.println(maxArea);
        System.out.println(maxAreaPlus);
    }

    static int BFS(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            int wall = 1;
            for (int i = 0; i < 4; i++) {
                if ((board[cur.x][cur.y] & wall) != wall) {
                    int xx = cur.x + dx[i]; int yy = cur.y + dy[i];

                    if (!isRange(xx, yy)) continue;
                    if (!visited[xx][yy]) {
                        q.add(new Node(xx, yy));
                        visited[xx][yy] = true;
                        cnt++;
                    }
                }
                wall <<= 1;
            }
        }
        return cnt;
    }

    static boolean isRange(int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        else return true;
    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x; this.y = y;
    }
}