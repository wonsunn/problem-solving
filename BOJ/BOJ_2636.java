import java.io.*;
import java.util.*;

public class BOJ_2636 {

	static int n, m, time, cnt;
	static int[][] board;
	static boolean[][] visited;
	
	static Queue<int[]> meltingCheeze;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			setAirPlaces();
			
			visited = new boolean[n][m];
			meltingCheeze = new LinkedList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (board[i][j] == 1 && isEdge(i,j)) {
						BFS(i, j);
					}
				}
			}
			
			if (meltingCheeze.isEmpty()) break;
			
			cnt = meltingCheeze.size();
			
			while (!meltingCheeze.isEmpty()) {
				int[] cur = meltingCheeze.poll();
				board[cur[0]][cur[1]] = -1;
			}
			
			time++;
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}
	
	static void BFS(int a, int b) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {a,b});
		visited[a][b] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			
			if (isEdge(x, y)) {
				board[x][y] = 2;
				meltingCheeze.add(new int[] {x,y});
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || board[nx][ny] != 1) continue;
				
				q.add(new int[] {nx,ny});
				visited[nx][ny] = true;
			}	
		}	
	}
	
	static boolean isEdge(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (board[nx][ny] == -1) return true;
		}
		
		return false;
	}
	
	static void setAirPlaces() {
		boolean[][] visitedAir = new boolean[n][m];
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {0,0});
		board[0][0] = -1;
		visitedAir[0][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visitedAir[nx][ny] || board[nx][ny] == 1) continue;
				
				q.add(new int[] {nx,ny});
				board[nx][ny] = -1;
				visitedAir[nx][ny] = true;
			}
		}
	}
}