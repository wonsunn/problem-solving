import java.util.*;

class Solution {
    
    static int n = 0;
    static Queue<Robot> q = new LinkedList<>();
    static boolean[][][][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[][] axis1 = new int[][]{{-1, 1, -1, 1}, {-1, -1, 1, 1}};
    static int[][] axis2 = new int[][]{{-1, 1, -1, 1}, {1, 1, -1, -1}};
    static int[][] check = new int[][]{{-1, 1, -1, 1}, {0, 0, 0, 0}};
    static int[][] newBoard;
        
    public int solution(int[][] board) {
        n = board.length;
        newBoard = board;
        visited = new boolean[n][n][n][n];
        
        q.add(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;

        return BFS();
    }
    
    static int BFS() {
        while (!q.isEmpty()) {
            Robot cur = q.poll();
            
            if ((cur.x1 == n - 1 && cur.y1 == n - 1) || (cur.x2 == n - 1 && cur.y2 == n - 1)) {
                return cur.cnt;
            }
            
            // 이동하기
            for (int i = 0; i < 4; i++) {
                int nx1 = cur.x1 + dx[i]; int nx2 = cur.x2 + dx[i];
                int ny1 = cur.y1 + dy[i]; int ny2 = cur.y2 + dy[i];
                
                if (isRange(nx1, ny1) && isRange(nx2, ny2)) {
                    if (!visited[nx1][ny1][nx2][ny2]) {
                        visited[nx1][ny1][nx2][ny2] = true;
                        q.add(new Robot(nx1, ny1, nx2, ny2, cur.cnt + 1));
                    }
                }
            }
            
            //회전하기 - 가로일 때
            if (cur.x1 == cur.x2) {
                // (x1, y1)이 왼쪽 위치
                if (cur.y1 < cur.y2) 
                    solve1(cur.x1, cur.y1, cur.x2, cur.y2, cur.cnt, axis1);
        
                // (x1, y1)이 오른쪽 위치
                else 
                    solve1(cur.x1, cur.y1, cur.x2, cur.y2, cur.cnt, axis2);
            }
            
            //회전하기 - 세로일 때
            else if (cur.y1 == cur.y2) {
                // (x1, y1)이 위쪽 위치
                if (cur.x1 < cur.x2) 
                    solve2(cur.x1, cur.y1, cur.x2, cur.y2, cur.cnt, axis1);

                // (x1, y1)이 아래쪽 위치
                else 
                    solve2(cur.x1, cur.y1, cur.x2, cur.y2, cur.cnt, axis2);
            }
        }
        return 0;
    }
    
    static void solve1(int x1, int y1, int x2, int y2, int cnt, int[][] axis) {
        for (int i = 0; i < 4; i++) {
            int nx1, ny1, nx2, ny2, c1, c2;
            // (x1, y1)이 축
            if (i == 0 || i == 1) {
                nx1 = x1; ny1 = y1;
                nx2 = x2 + axis[0][i]; ny2 = y2 + axis[1][i];
                c1 = x2 + check[0][i]; c2 = y2 + check[1][i];
            }
            // (x2, y2)이 축
            else {
                nx1 = x1 + axis[0][i]; ny1 = y1 + axis[1][i];
                nx2 = x2; ny2 = y2;
                c1 = x1 + check[0][i]; c2 = y1 + check[1][i];
            }

            if (isRange(nx1, ny1) && isRange(nx2, ny2) && isRange(c1, c2)) {
                if (!visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.add(new Robot(nx1, ny1, nx2, ny2, cnt + 1));
                }
            } 
        }
    }
    
    static void solve2(int x1, int y1, int x2, int y2, int cnt, int[][] axis) {
        for (int i = 0; i < 4; i++) {
            int nx1, ny1, nx2, ny2, c1, c2;
            // (x1, y1)이 축
            if (i == 0 || i == 1) {
                nx1 = x1; ny1 = y1;
                nx2 = x2 + axis[1][i]; ny2 = y2 + axis[0][i];
                c1 = x2 + check[1][i]; c2 = y2 + check[0][i];
            }
            // (x2, y2)이 축
            else {
                nx1 = x1 + axis[1][i]; ny1 = y1 + axis[0][i];
                nx2 = x2; ny2 = y2;
                c1 = x1 + check[1][i]; c2 = y1 + check[0][i];
            }

            if (isRange(nx1, ny1) && isRange(nx2, ny2) && isRange(c1, c2)) {
                if (!visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.add(new Robot(nx1, ny1, nx2, ny2, cnt + 1));
                }
            } 
        }
    }
    
    static boolean isRange(int a, int b) {
        if (a < 0 || a >= n || b < 0 || b >= n) return false;
        if (newBoard[a][b] == 1) return false;
        return true;
    }
}

class Robot {
    int x1, y1, x2, y2, cnt;
    
    Robot (int x1, int y1, int x2, int y2, int cnt) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
        this.cnt = cnt;
    }
}