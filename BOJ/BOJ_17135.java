import java.io.*;
import java.util.*;

class Main {

    static int n, m, d, res = Integer.MIN_VALUE;
    static int[][] board, copyBoard;
    static Node[] g = new Node[3];
    static List<Node> list = new ArrayList<>(); // 적 제거리스트 저장하기 위함

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

        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        DFS(0, 0);

        System.out.println(res);
    }

    static void DFS(int start, int level) {
        if (level == 3) {
            copyBoard = new int[n][m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    copyBoard[i][j] = board[i][j];

            int ans = 0;
            while (true) {
                boolean flag = true;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (copyBoard[i][j] == 1) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag)
                    break;

                list.clear();
                for (Node node : g)
                    recordEnemy(node, copyBoard);

                ans += killAndMove(copyBoard);
            }

            res = Math.max(res, ans);

            return;
        }

        for (int i = start; i < m; i++) {
            g[level] = new Node(n, i);
            DFS(i + 1, level + 1);
        }
    }

    static void recordEnemy(Node node, int[][] board) {
        Node tmp = new Node(16, 16);
        int min = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) { 
                int dis = getDistance(node, i, j);
                
                if (board[i][j] == 1 && dis <= d) {
                    if (dis < min) {
                        min = dis;
                        tmp.x = i; tmp.y = j;
                    }
                    else if (dis == min && j < tmp.y) {
                        tmp.x = i; tmp.y = j;
                    }
                }
            }
        }

        if (tmp.x == 16 || tmp.y == 16) return;

        list.add(tmp);
    }

    static int killAndMove(int[][] board) {
        int cnt = 0;
        // 적 죽이기
        for (Node node : list) {
            if (board[node.x][node.y] == 1) {
                board[node.x][node.y] = 0;
                cnt++;
            }
        }

        // 한 칸씩 내리기
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++)
                board[i + 1][j] = board[i][j];
        }

        for (int j = 0; j < m; j++)
            board[0][j] = 0;

        return cnt;
    }

    static int getDistance(Node node, int x, int y) {
        return Math.abs(node.x - x) + Math.abs(node.y - y);
    }
}
