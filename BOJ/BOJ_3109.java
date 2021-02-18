import java.io.*;
import java.util.*;

public class BOJ_3109 {

	static int r, c, cnt;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) map[i] = br.readLine().toCharArray();
        
        for (int i = 0; i < r; i++) makePipeline(i, 0);
        System.out.println(cnt);
	}
	
	static boolean makePipeline(int x, int y) {
		if (y == c - 1) {
			cnt++;
			return true;
		}
		
		int nx, ny = y + 1;
		for (int i = 0; i < 3; i++) {
			nx = x + dx[i];
			
			if (nx < 0 || nx >= r || map[nx][ny] == 'x' || visited[nx][ny]) continue;
		
			visited[nx][ny] = true;
			if (makePipeline(nx, ny)) return true;
		}	
		
		return false;
	}
}