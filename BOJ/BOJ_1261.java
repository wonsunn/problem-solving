import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static PriorityQueue<Node> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    map = new char[m][n];
	    visited = new boolean[m][n];

	    for (int i = 0; i < m; i++)
	        map[i] = br.readLine().toCharArray();

        System.out.println(BFS());
    }

    static int BFS() {
        q = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node n1, Node n2) {
                return n1.cnt - n2.cnt;
            }
        });
        q.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == m - 1 && cur.y == n - 1)
                return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;

                if (!visited[nx][ny]) {
                    if (map[nx][ny] == '0')
                        q.add(new Node(nx, ny, cur.cnt));
                    else
                        q.add(new Node(nx, ny, cur.cnt + 1));

                    visited[nx][ny] = true;
                }
            }
        }
        return 0;
    }
}

class Node {
    int x, y, cnt;

    Node (int x, int y, int cnt) {
        this.x = x; this.y = y; this.cnt = cnt;
    }
}