import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int r,c,n;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for (int i = 0; i < r; i++) map[i] = br.readLine().toCharArray();

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int h = r - Integer.parseInt(st.nextToken());
            breakMineral(h, i);

            solve();
        }

        for (int i = 0; i < r; i++) {
            sb.append(map[i]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // 미네랄 깨뜨리기
    static void breakMineral(int height, int where) {
        if (where % 2 == 0) {
            for (int i = 0; i < c; i++) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    break;
                }
            }
        }
        else {
            for (int i = c - 1; i >= 0; i--) {
                if (map[height][i] == 'x') {
                    map[height][i] = '.';
                    break;
                }
            }
        }
    }

    static void solve() {
        visited = new boolean[r][c];
        List<Node> cluster = new ArrayList<>();

        // 땅과 붙어있는 미네랄 찾기
        for (int j = 0; j < c; j++) {
            if (map[r - 1][j] == 'x') {
                visited[r - 1][j] = true;
                q.add(new Node(r - 1, j));
            }
        }

        // 상,하,좌,우로 인접한 미네랄 visit 처리
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int xx = cur.x + dx[i];
                int yy = cur.y + dy[i];

                if (isRange(xx, yy) && !visited[xx][yy] && map[xx][yy] == 'x') {
                    q.add(new Node(xx, yy));
                    visited[xx][yy] = true;
                }
            }
        }

        // 공중에 떠있는(visit되지 않은) 미네랄 클러스터에 추가
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 'x' && !visited[i][j]) {
                    cluster.add(new Node(i, j));
                    map[i][j] = '.';
                }
            }
        }

        // 공중에 떠있는 미네랄 없으면 다음 막대 던지러
        if (cluster.isEmpty()) return;

        // 공중에 떠있는 미네랄 내리는 작업
        boolean down = true;
        while (down) {
            for (Node node : cluster) {
                int xx = node.x + 1; int yy = node.y;

                // 범위 벗어나거나 미네랄 있으면 그만 내리기
                if (!isRange(xx, yy) || map[xx][yy] == 'x') {
                    down = false;
                    break;
                }
            }

            if (down) {
                for (Node node : cluster)
                    node.x++;
            }
        }

        // 최종 위치에 mark하기
        for (Node node : cluster)
            map[node.x][node.y] = 'x';
    }

    static boolean isRange(int x, int y) {
        if( x < 0 || x >= r || y < 0 || y >= c) return false;
        return true;
    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x; this.y = y;
    }
}