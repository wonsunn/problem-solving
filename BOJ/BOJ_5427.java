import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int t, w, h;
    static char[][] map;
    static Queue<Node> fire, loc;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    t = Integer.parseInt(br.readLine());

        for (int k = 0; k < t; k++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            visited = new boolean[h][w];

            fire = new LinkedList<>();
            loc = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*')
                        fire.add(new Node(i, j));
                    if (map[i][j] == '@') {
                        visited[i][j] = true;
                        loc.add(new Node(i, j));
                    }
                }
            }

            int time = 0;
            while (true) {
                time++;
                fireMoved();
                int res = move();
                if (res == 1) {
                    System.out.println(time);
                    break;
                }
                else if (res == -1) {
                    System.out.println("IMPOSSIBLE");
                    break;
                }
            }
        }
    }

    // 불 퍼뜨리기
    static void fireMoved() {
        int size = fire.size();
        for (int i = 0; i < size; i++) {
            Node cur = fire.poll();
            for (int j = 0; j < 4; j++) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    continue;
                if (map[nx][ny] == '#' || map[nx][ny] == '*')
                    continue;

                map[nx][ny] = '*';
                fire.add(new Node(nx, ny));
            }
        }
    }

    // 사람 이동시키기(return 1 : 탈출, return 0 : 이동 가능(only 이동), return -1 : 이동할 곳 x)
    static int move() {
        boolean flag = false;
        int size = loc.size();
        for (int i = 0; i < size; i++) {
            Node cur = loc.poll();
            for (int j = 0; j < 4; j++) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                    return 1;
                if (visited[nx][ny] || map[nx][ny] == '*' || map[nx][ny] == '#')
                    continue;

                flag = true;
                visited[nx][ny] = true;
                loc.add(new Node(nx, ny));
            }
        }
        if (!flag) return -1;

        return 0;
    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x; this.y = y;
    }
}