import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, cnt = 0, area, maxArea = 0;
    static int[][] board;
    static int[][] visited; // 방의 번호를 저장하면서 visit 처리 
    static int[] wall = new int[4]; // 벽의 여부 저장
    static int[] roomSize = new int[2501]; // 각 방의 크기 저장
    static boolean[][] link = new boolean[2501][2501]; // 서로 다른 방이 앞/뒤/좌/우로 붙어 있는 경우 저장
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[m][n];
        visited = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j< n; j++) {
                if (visited[i][j] == 0) {
                    area = 0;
                    DFS(i, j, ++cnt);
                    maxArea = Math.max(maxArea, area);
                    roomSize[cnt] = area;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxArea);
        System.out.println(findAnswer3());
    }

    // 각 방마다 벽의 위치를 조회
    static void whichWall(int val) {
        Arrays.fill(wall, 0);

        int idx = 0;
        while (val != 0) {
            int na = val % 2;
            val /= 2;
            wall[idx++] = na;
        }
    }

    static void DFS(int x, int y, int cnt) {
        visited[x][y] = cnt;
        area++;
        whichWall(board[x][y]);

        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i]; int yy = y + dy[i];
            if (!isRange(xx, yy)) continue;

            if (wall[i] == 0) {
                if (visited[xx][yy] == 0) {
                    DFS(xx, yy, cnt);
                    whichWall(board[x][y]);
                }
            }
            else {
                if (visited[xx][yy] > 0 && visited[xx][yy] != cnt
                        && !link[visited[xx][yy]][cnt]) {
                    link[visited[xx][yy]][cnt] = true;
                }
            }
        }
    }

    // 하나의 벽을 제거할 때 얻을 수 있는 가장 넓은 방의 크기 구하기
    static int findAnswer3() {
        int max = 0;
        for (int i = 1; i <= cnt; i++) {
            for (int j = 1; j <= cnt; j++) {
                if (link[i][j]) {
                    int tmp = roomSize[i] + roomSize[j];
                    max = Math.max(max, tmp);
                }
            }
        }
        return max;
    }

    static boolean isRange(int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        else return true;
    }
}