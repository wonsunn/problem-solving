import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[][] board, air;
    static Queue<Pair> q = new LinkedList<>();
    static List<Pair> cheese = new ArrayList<>();
    static List<Pair> list = new ArrayList<>();
  
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    private static class Pair {
        int x, y;
        boolean flag;

        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }

        Pair (int x, int y, boolean flag) {
            this.x = x;
            this.y = y;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        air = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    air[i][j] = -1;
                    list.add(new Pair(i, j, false));
                }
            }
        }

        setAirState();

        int hour = 0;
        while (true) {
            if (isClear()) {
                System.out.println(hour);
                break;
            }
            findCheeses();
            meltCheeses();
            combineAir();

            hour++;
        }
    }

    /*
    녹는 치즈들을 선정하는 과정
     */
    static void findCheeses() {
        cheese.clear();

        for (int i = 0; i < list.size(); i++) {
            Pair cur = list.get(i);

            if (cur.flag == true) continue; // 이미 녹은 치즈는 탐색할 필요 없음

            int x = cur.x;
            int y = cur.y;
            int cnt = 0;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (board[nx][ny] == 0 && air[nx][ny] == 1)
                    cnt++;
            }

            if (cnt >= 2) {
                cheese.add(new Pair(x, y));
                cur.flag = true;
            }
        }
    }

    /*
    치즈를 실제로 녹이는 과정
    그리고, 외부공기 + 내부공기를 합치기 위한 큐에 저장
     */
    static void meltCheeses() {
        for (int i = 0; i < cheese.size(); i++) {
            int x = cheese.get(i).x;
            int y = cheese.get(i).y;

            board[x][y] = 0;
            q.add(new Pair(x, y));
        }
    }

    /*
    외부공기와 내부공기를 합치는 과정
     */
    static void combineAir() {
        while (!q.isEmpty()) {
            int x = q.peek().x;
            int y = q.peek().y;
            q.poll();

            air[x][y] = 1;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (air[nx][ny] == 0) {
                    air[nx][ny] = 1;
                    q.add(new Pair(nx, ny));
                }
            }
        }
    }

    static boolean isClear() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1)
                    return false;
            }
        }

        return true;
    }

    static void setAirState() {
        q.add(new Pair(0, 0));
        air[0][0] = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (air[nx][ny] == 0) {
                        air[nx][ny] = 1;
                        q.add(new Pair(nx, ny));
                    }
                }
            }
        }
    }
}