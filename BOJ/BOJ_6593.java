import java.io.*;
import java.util.*;

class Main {

    static int l, r, c, El, Ex, Ey;
    static char[][][] building;
    static boolean[][][] visited;
    static Queue<Pair> q;

    static int[] dl = {1, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Pair {
        int level, x, y, time;

        Pair (int level, int x, int y, int time) {
            this.level = level;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0)
                break;

            q = new LinkedList<>();
            building = new char[l][r][c];
            visited = new boolean[l][r][c];

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String tmp = br.readLine();
                    for (int k = 0; k < c; k++) {
                        building[i][j][k] = tmp.charAt(k);
                        if (building[i][j][k] == 'S')
                            q.add(new Pair(i, j, k, 0));
                        if (building[i][j][k] == 'E') {
                            El = i;
                            Ex = j;
                            Ey = k;
                        }
                    }
                }
                br.readLine(); // 빈 줄 읽기
            }

            int res = BFS();
            if (res != -1) System.out.println("Escaped in " + res + " minute(s).");
            else System.out.println("Trapped!");
        }
    }

    static int BFS() {
        while (!q.isEmpty()) {
            int level = q.peek().level;
            int x = q.peek().x;
            int y = q.peek().y;
            int time = q.peek().time;

            q.poll();

            if (level == El && x == Ex && y == Ey)
                return time;

            // 동,서,남,북 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (building[level][nx][ny] != '#' && !visited[level][nx][ny]) {
                    q.add(new Pair(level, nx, ny, time + 1));
                    visited[level][nx][ny] = true;
                }
            }

            // 상,하 탐색
            for (int i = 0; i < 2; i++) {
                int nl = level + dl[i];

                if (nl < 0 || nl >= l) continue;
                if (building[nl][x][y] != '#' && !visited[nl][x][y]) {
                    q.add(new Pair(nl, x, y, time + 1));
                    visited[nl][x][y] = true;
                }
            }
        }

        return -1;
    }
}