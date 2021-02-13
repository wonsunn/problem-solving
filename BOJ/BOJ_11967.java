import java.io.*;
import java.util.*;

public class Main {

    static int n, m, cnt = 1;
    static boolean[][] room, visited;
    static List<Node>[][] switches;
    static Queue<Node> q = new LinkedList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

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
        m = Integer.parseInt(st.nextToken());

        room = new boolean[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        switches = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
            switches[x][y].add(new Node(a, b));
        }

        room[1][1] = true;
        visited[1][1] = true;
        q.add(new Node(1, 1));

        BFS();

        System.out.println(cnt);
    }

    static void BFS() {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            switchOn(cur.x, cur.y);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                // 아직 방문하지 않고 불이 켜져 있는 방이라면 이동
                if (room[nx][ny] && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    static void switchOn(int x, int y) {
        for (int i = 0; i < switches[x][y].size(); i++) {
            int a = switches[x][y].get(i).x;
            int b = switches[x][y].get(i).y;

            // 이미 불이 켜져 있으면 패스
            if (room[a][b]) continue;

            room[a][b] = true;
            cnt++;

            // 불 켜진 방으로 주변(동서남북) 방문한 방으로 부터 이동할 수 있으면 이미 방문한 방 위치 큐에 추가
            for (int j = 0; j < 4; j++) {
                int nx = a + dx[j];
                int ny = b + dy[j];

                if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                if (visited[nx][ny]) {
                    q.add(new Node(nx, ny));
                    break;
                }
            }
        }
    }
}