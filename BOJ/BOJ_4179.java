import java.io.*;
import java.util.*;

class Main {

    static int r, c;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static Queue<Info> jq = new LinkedList<>();
    static Queue<Info> fq = new LinkedList<>();

    private static class Info {
        int x, y;

        Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'J') {
                    jq.add(new Info(i, j));
                    visited[i][j] = true;
                }

                if (map[i][j] == 'F')
                    fq.add(new Info(i, j));
            }
        }

        int cnt = 0;
        while (true) {
            firing();

            int val = movePerson();
            cnt++;

            if (val != 0) {
                if (val == 1) System.out.println(cnt);
                else System.out.println("IMPOSSIBLE");

                break;
            }
        }
    }

    static int movePerson() {
        boolean move = false;

        int size = jq.size();
        for (int i = 0; i < size; i++) {
            Info cur = jq.poll();

            for (int j = 0; j < 4; j++) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c)
                    return 1;
                if (!visited[nx][ny] && map[nx][ny] != '#' && map[nx][ny] != 'F') {
                    jq.add(new Info(nx, ny));
                    visited[nx][ny] = true;
                    move = true;
                }
            }
        }

        if (!move) return -1;

        return 0;
    }

    static void firing() {
        int size = fq.size();
        for (int i = 0; i < size; i++) {
            Info cur = fq.poll();

            for (int j = 0; j < 4; j++) {
                int nx = cur.x + dx[j];
                int ny = cur.y + dy[j];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (map[nx][ny] == '.' || map[nx][ny] == 'J') {
                    fq.add(new Info(nx, ny));
                    map[nx][ny] = 'F';
                }
            }
        }
    }
}