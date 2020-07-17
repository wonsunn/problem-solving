import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n, k;
    static int[][] board, visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> cntList = new ArrayList<>();
        int[] node = new int[4];
        int res = 0;

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[m][n];
        visited = new int[m][n];

        for (int t = 0; t < k; t++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) node[i] = Integer.parseInt(st.nextToken());

            for (int i = m - node[3]; i < m - node[1]; i++)
                for (int j = node[0]; j < node[2]; j++)
                    board[i][j] = 1;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 && visited[i][j] == 0){
                    res++;
                    cntList.add(BFS(i, j));
                }
            }
        }
        Collections.sort(cntList);

        System.out.println(res);
        for (int c : cntList) System.out.print(c + " ");
    }

    static int BFS(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = 1;
        int cnt = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int xx = cur.x + dx[i]; int yy = cur.y + dy[i];

                if (xx < 0 || xx >= m || yy < 0 | yy >= n) continue;
                if (board[xx][yy] == 0 && visited[xx][yy] == 0) {
                    q.add(new Node(xx, yy));
                    visited[xx][yy] = 1;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x; this.y = y;
    }
}