import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[][] area;
    static int[][][] time;

    static Queue<Pair> q = new LinkedList<>();

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static class Pair {
        int x, y, bridge;
        boolean beforeBridge;

        Pair (int x, int y, int bridge, boolean beforeBridge) {
            this.x = x;
            this.y = y;
            this.bridge = bridge;
            this.beforeBridge = beforeBridge;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new int[n][n];
        time = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());

                time[i][j][0] = 987654321;
                time[i][j][1] = 987654321;
            }
        }

        // 교차지점 -1로 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j] == 0 && isCrossArea(i, j))
                    area[i][j] = -1;
            }
        }

        BFS();
        System.out.println(Math.min(time[n - 1][n - 1][0], time[n - 1][n - 1][1]));
    }

    static void BFS() {
        q.add(new Pair(0, 0, 0,false));
        time[0][0][0] = 0;

        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            int bridge = q.peek().bridge;
            boolean beforeBridge = q.peek().beforeBridge;

            q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || area[nx][ny] == -1) continue;

                if (area[nx][ny] == 1) {
                    if (time[nx][ny][bridge] > time[x][y][bridge] + 1) {
                        time[nx][ny][bridge] = time[x][y][bridge] + 1;
                        q.add(new Pair(nx, ny, bridge, false));
                    }
                }
                else if (area[nx][ny] == 0) {
                    if (bridge == 0 && !beforeBridge) {
                        int targetTime = setTime(time[x][y][bridge] + 1, m);
                        if (time[nx][ny][bridge] > targetTime) {
                            time[nx][ny][1] = targetTime;
                            q.add(new Pair(nx, ny, 1, true));
                        }
                    }
                }
                else if (area[nx][ny] >= 2) {
                    if (!beforeBridge) {
                        int targetTime = setTime(time[x][y][bridge] + 1, area[nx][ny]);

                        if (time[nx][ny][bridge] > targetTime) {
                            time[nx][ny][bridge] = targetTime;
                            q.add(new Pair(nx, ny, bridge, true));
                        }
                    }
                }
            }
        }
    }

    // 다리가 놓여질 수 있는 시간 구하기
    static int setTime(int t, int period) {
        while (true) {
            if (t % period == 0) break;
            t++;
        }

        return t;
    }

    // 교차지점 판단
    static boolean isCrossArea(int x, int y) {
        boolean weight = false, height = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

            if (i % 2 == 0 && area[nx][ny] != 1) height = true;
            if (i % 2 != 0 && area[nx][ny] != 1) weight = true;
        }

        if (weight && height) return true;
        else return false;
    }
}