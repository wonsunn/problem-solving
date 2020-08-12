import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, k;
    static char[][] map;
    static int[][][] visited;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    k = Integer.parseInt(st.nextToken());
	    map = new char[n][m];
	    visited = new int[n][m][k + 1];

	    for (int i = 0; i < n; i++)
            map[i] = br.readLine().toCharArray();

        System.out.println(BFS());
    }

    static int BFS() {
        q.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == n - 1 && cur.y == m - 1)
                return visited[cur.x][cur.y][cur.cnt];

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (visited[nx][ny][cur.cnt] != 0)
                    continue;

                if (map[nx][ny] == '0') {
                    if (visited[nx][ny][cur.cnt] == 0) {
                        q.add(new Node(nx, ny, cur.cnt));
                        visited[nx][ny][cur.cnt] = visited[cur.x][cur.y][cur.cnt] + 1;
                    }

                }
                else {
                    if (cur.cnt != k && visited[nx][ny][cur.cnt + 1] == 0) {
                        q.add(new Node(nx, ny, cur.cnt + 1));
                        visited[nx][ny][cur.cnt + 1] = visited[cur.x][cur.y][cur.cnt] + 1;
                    }
                }
            }
        }
        return -1;
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