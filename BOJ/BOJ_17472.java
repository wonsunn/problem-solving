import java.io.*;
import java.util.*;

public class BOJ_17472 {

	static int n, m, res;
	static int[][] map, connected;
	static boolean[][] visited;
	static int[] parents;
	
	static List<Node> bridge_list = new ArrayList<>();
	
	static class Node implements Comparable<Node> {
		int x, y, len;
		
		Node (int x, int y, int len) {
			this.x = x; this.y = y; this.len = len;
		}

		@Override
		public int compareTo(Node n) {
			return this.len - n.len;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		
		int island_cnt = 0, bridge_cnt = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) 
					setIsland(i, j, ++island_cnt);
			}
		}
		
		parents = new int[island_cnt + 1];
		for (int i = 1; i <= island_cnt; i++) parents[i] = i;
		connected = new int[island_cnt + 1][island_cnt + 1];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] > 0) 
					getBridges(i, j, map[i][j]);
			}
		}
		
		Collections.sort(bridge_list); // 최소 거리 기준으로 정렬
		
		boolean isPossible = false;
		for (Node cur : bridge_list) {
			if (isSameParents(cur.x, cur.y)) continue;
			
			union(cur.x, cur.y);
			bridge_cnt++;
			res += cur.len;
			
			if (bridge_cnt == island_cnt - 1) {
				isPossible = true;
				break;
			}
		}
		
		if (isPossible) System.out.println(res);
		else System.out.println(-1);
	}
	
	/*
	 * 각각의 섬들을 연결하는 모든 다리를 리스트에 저장하는 함수
	 */
	static void getBridges(int x, int y, int from) {
		for (int i = 0; i < 4; i++) {
			int nx = x, ny = y, cnt = 0;
			while (true) {
				int xx = nx + dx[i];
				int yy = ny + dy[i];
				
				if (!isRange(xx, yy) || map[xx][yy] == from) break;
				
				if (map[xx][yy] > 0) {
					int to = map[xx][yy];
					
					if (cnt == 1 || (connected[from][to] != 0 && cnt >= connected[from][to])) break;
					
					bridge_list.add(new Node(from, to, cnt));
					connected[from][to] = cnt;
					connected[to][from] = cnt;
					
					break;	
				}
				
				cnt++;
				nx = xx;
				ny = yy;
			}
		}
	}
	
	/*
	 * 섬의 인덱스 정해주는 함수
	 */
	static void setIsland(int a, int b, int num) {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {a,b});
		visited[a][b] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			
			map[x][y] = num;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) continue;
				
				q.add(new int[] {nx,ny});
				visited[nx][ny] = true;
			}
		}
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return;
		if (a < b) parents[b] = a;
		else parents[a] = b;
	}
	
	static int find(int a) {
		if (a == parents[a]) return a;
		else return parents[a] = find(parents[a]);
	}
	
	static boolean isSameParents(int a, int b) {
		return find(a) == find(b);
	}
	
	static boolean isRange(int nx, int ny) {
		if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false;
		else return true;
	}
	
}