import java.io.*;
import java.util.*;

public class BOJ_16236 {
	
	static final int MAX = Integer.MAX_VALUE;
	static int n, time, shark = 2, eat;
	static int[][] map;
	static boolean[][] visited;
	
	static Queue<int[]> q = new LinkedList<>();
	static PriorityQueue<int[]> pq;
	
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < n; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		if (map[i][j] == 9) {
        			map[i][j] = 0;
        			q.add(new int[] {i,j,0});
        		}
        	}
        }
        
        while (true) {
        	pq = new PriorityQueue<>((i1, i2) -> i1[0] == i2[0] ? i1[1] - i2[1] : i1[0] - i2[0]); // 거리가 같은 물고기들을 처리하기 위한 우선순위 큐
        	int moveCnt = searchFish(); // 물고기를 먹을 수 있는 최단 거리를 반환
        	// 먹을 수 있는 물고기가 없다면(엄마 상어에게 도움 요청을 해야한다면) 종료	
        	if (moveCnt == MAX) 
        		break;
        	
        	eatFish(); // 우선순위 큐의 첫번 째 위치에 있는 물고기를 먹음
        	 
        	time += moveCnt; // 이동 수만큼 시간 증가
        }
        
        System.out.println(time);
	}
	
	static void eatFish() {
		int x = pq.peek()[0];
		int y = pq.peek()[1];
		// 물고기 먹은 위치에서 다시 탐색 시작하기 위해 큐 처음 위치 갱신
		q.clear();
		q.add(new int[] {x,y,0}); 
		
		eat++; // 먹은 수 증가
		map[x][y] = 0; // 먹은 물고기 빈칸 처리
		
		// 상어 크기 수만큼 물고기를 먹으면 상어 크기 1 증가, 먹은 수 0으로 초기화
		if (eat == shark) {
			shark++;
			eat = 0;
		}
	}
	
	static int searchFish() {
		visited = new boolean[n][n];
		
		int min = MAX;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			
			// 최단 거리보다 이동 수가 더 커지면 큐 종료
			if (cur[2] > min) break;
			
			// 상어 크기보다 작은 물고기를 만났다면
			if (map[x][y] != 0 && map[x][y] < shark && cur[2] <= min) {
				pq.add(new int[] {x,y});
				min = cur[2];
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 범위를 벗어나거나, 방문했거나, 물고기의 크기가 상어보다 크면 제외
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || map[nx][ny] > shark) continue;
				
				q.add(new int[] {nx,ny,cur[2] + 1});
				visited[nx][ny] = true;
			}
		}
		
		return min;
	}

}