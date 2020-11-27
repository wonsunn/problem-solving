import java.io.*;
import java.util.*;

class Main {

    static int n, destX, destY;
    static char[][] home;
    static Queue<Info> q = new LinkedList<>();
    static int visited[][][];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    private static class Info {
        int x, y, dir;

        Info (int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        home = new char[n][n];
        visited = new int[n][n][4];

        int tmp = 0;
        for (int[][] matrix : visited)
            for (int[] row : matrix)
                Arrays.fill(row, -1);

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                home[i][j] = s.charAt(j);
                if (home[i][j] == '#') {
                    if (tmp == 0) {
                        for (int k = 0; k < 4; k++) {
                            q.add(new Info(i, j, k));
                            visited[i][j][k] = 0;
                        }
                        tmp++;
                    }
                    else {
                        destX = i; destY = j;
                    }

                    home[i][j] = '!';
                }
            }
        }

        System.out.println(BFS());
    }

    static int BFS() {
        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            int dir = q.peek().dir;

            q.poll();

            if (x == destX && y == destY) {
                return visited[x][y][dir];
            }

            int nx = x + dx[dir], ny = y + dy[dir];
            while (move(nx, ny) && home[nx][ny] == '.') {
                nx += dx[dir];
                ny += dy[dir];
            }

            if (move(nx, ny) && home[nx][ny] == '!') {
                visited[nx][ny][dir] = visited[x][y][dir];
                q.add(new Info(nx, ny, dir));

                int d = dir < 2 ? 2 : 0;
                for (int i = d; i < d + 2; i++) {
                    if (visited[nx][ny][i] == -1) {
                        visited[nx][ny][i] = visited[x][y][dir] + 1;
                        q.add(new Info(nx, ny, i));
                    }
                }
            }
        }

        return 0;
    }

    static boolean move(int nx, int ny) {
        if (nx < 0 || nx >= n || ny < 0 || ny >= n || home[nx][ny] == '*') return false;
        else return true;
    }
}