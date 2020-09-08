import java.util.*;

class Solution {
    
    static int n, res = Integer.MAX_VALUE;
    static int[][] visited;
    static Queue<Info> q = new LinkedList<>();
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    
    public int solution(int[][] board) {
        n = board.length;
        visited = new int[n][n];
        
        for (int[] row : visited)
            Arrays.fill(row, -1);
        
        q.add(new Info(0, 0, 0, -1));
        visited[0][0] = 0;
        BFS(board);
        
        return res;
    }
    
    static void BFS(int[][] board) {
        while (!q.isEmpty()) {
            Info cur = q.poll();
            
            if (cur.x == n - 1 & cur.y == n - 1) 
                res = Math.min(res, cur.cost);
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i]; int ny = cur.y + dy[i]; int newCost = 0;
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) continue;
                
                if (cur.dir == -1 || cur.dir == i)
                    newCost = cur.cost + 100;
                else if (cur.dir != i)
                    newCost = cur.cost + 600;
                
                if (visited[nx][ny] == -1 || visited[nx][ny] >= newCost) {
                    visited[nx][ny] = newCost;
                    q.add(new Info(nx, ny, newCost, i));
                }
            }
        }
    }
}

class Info {
    int x, y, cost, dir;
    
    Info (int x, int y, int cost, int dir) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.dir = dir;
    }
}