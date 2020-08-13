import java.util.*;

class Solution {
    
    static boolean[][] visited;
    static Queue<Node> q;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, getSizeOfArea(i, j, m, n, picture));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    static int getSizeOfArea(int x, int y, int m, int n, int[][] picture) {
        q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;
        
        int cnt = 1;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[nx][ny] || picture[nx][ny] != picture[cur.x][cur.y]) continue;
                
                cnt++;
                q.add(new Node(nx, ny)); 
                visited[nx][ny] = true;
            }
        }
        return cnt;
    }
}

class Node {
    int x, y;
    
    Node(int x, int y) {
        this.x = x; this.y = y;
    }
}