import java.io.*;
import java.util.*;

public class BOJ_1194 {

	static int n, m;
	static char[][] miro;
	static boolean[][][] visited;
	static Queue<Info> q = new LinkedList<>();
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Info {
		int x, y, dis, keys;
		
		Info (int x, int y, int dis, int keys) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.keys = keys;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        miro = new char[n][m];
        visited = new boolean[n][m][64];
        
        for (int i = 0; i < n; i++) {
        	String tmp = br.readLine();
        	for (int j = 0; j < m; j++) {
        		miro[i][j] = tmp.charAt(j);
        		if (miro[i][j] == '0') {
        			q.add(new Info(i,j,0,0));
        			visited[i][j][0] = true;
        		}
        	}
        }
        
        System.out.println(BFS());
	}
	
	static int BFS() {
		while (!q.isEmpty()) {
			Info cur = q.poll();
			int x = cur.x, y = cur.y, dis = cur.dis;
			
			if (miro[x][y] == '1') 
				return dis;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int keys = cur.keys;
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny][keys] || miro[nx][ny] == '#') 
					continue;
				
				if (97 <= miro[nx][ny] && miro[nx][ny] <= 102) 
					keys |= (1 << miro[nx][ny]-32-'A');
				
				if (65 <= miro[nx][ny] && miro[nx][ny] <= 70) {
					int val = 1 << miro[nx][ny]-'A';
					if ((keys & val) != val)
						continue;
				}
				
				q.add(new Info(nx,ny,dis+1,keys));
				visited[nx][ny][keys] = true;
			}
		}
		
		return -1;
	}

}