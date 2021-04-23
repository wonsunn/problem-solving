import java.io.*;
import java.util.*;

public class BOJ_15683 {

	static int n, m, res = Integer.MAX_VALUE;
	static int[][] map;
	static List<int[]> cctv = new ArrayList<>();
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static int[][][] dir = {{}, {{0},{1},{2},{3}}, {{0,2},{1,3}},{{0,1},{1,2},{2,3},{3,0}},{{0,1,3},{0,1,2},{1,2,3},{0,2,3}},{{0,1,2,3}}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < m; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if (0 < map[i][j] && map[i][j] < 6)
        			cctv.add(new int[] {i,j,map[i][j]});
        	}
        }
        
        DFS(0);
        System.out.println(res);
	}
	
	static void DFS(int idx) {
		if (idx == cctv.size()) {
			res = Math.min(res, countArea());
			return;
		}
		
		int[] cur = cctv.get(idx);
		int x = cur[0], y = cur[1], kind = cur[2];
		
		for (int i = 0; i < dir[kind].length; i++) {
			int[][] copyMap = new int[n][m];
			copyArray(copyMap, map);
			
			for (int j = 0; j < dir[kind][i].length; j++) setCctv(x, y, dir[kind][i][j], -1);
			DFS(idx + 1);
			
			copyArray(map, copyMap);
		}
	}
	
	static void setCctv(int x, int y, int d, int status) {
		while (true) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 6) break;
			
			x = nx;
			y = ny;
			
			if (map[nx][ny] > 0) continue;
			map[nx][ny] = status;
				
		}
	}
	
	static int countArea() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) cnt++;
			}
		}
		
		return cnt;
	}
	
	static void copyArray(int[][] A, int[][] B) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				A[i][j] = B[i][j];
			}
		}
	}

}