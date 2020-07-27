import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static char[][] puyo = new char[12][6];
    static boolean[][] visited;
    static Queue<Node> q;
    static List<Node> broken;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int num = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) puyo[i] = br.readLine().toCharArray();

        while (true) {
            visited = new boolean[12][6];
            boolean isBroken = false;

            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (puyo[i][j] != '.' && !visited[i][j]) {
                        if (BFS(i, j, puyo[i][j]) >= 4) {
                            isBroken = true;
                            broken.forEach(node -> puyo[node.x][node.y] = '.');
                        }
                    }
                }
            }

            if (isBroken) {
                for (int i = 0; i < 6; i++) {
                    while (true) {
                        boolean flag = false;
                        for (int j = 11; j >= 1; j--) {
                            if (puyo[j][i] == '.' && puyo[j - 1][i] != '.') {
                                puyo[j][i] = puyo[j - 1][i];
                                puyo[j - 1][i] = '.';
                                flag = true;
                            }
                        }

                        if (!flag) break;
                    }
                }
                num++;
            }
            else break;
        }
        System.out.println(num);
    }

    static int BFS(int x, int y, char color) {
        int cnt = 1;
        broken = new ArrayList<>();
        q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            broken.add(new Node(cur.x, cur.y));

            for (int i = 0; i < 4; i++) {
                int xx = cur.x + dx[i]; int yy = cur.y + dy[i];

                if (isRange(xx, yy) && !visited[xx][yy] && puyo[xx][yy] == color) {
                    q.add(new Node(xx, yy));
                    visited[xx][yy] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean isRange(int x, int y) {
        if (x < 0 || x > 11 || y < 0 || y > 5) return false;
        else return true;
    }
}

class Node {
    int x, y;

    Node (int x, int y) {
        this.x = x; this.y = y;
    }
}