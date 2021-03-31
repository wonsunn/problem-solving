import java.io.*;
import java.util.*;

public class BOJ_17071 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		if (n == k) {
			System.out.println(0);
			System.exit(0);
		}
		
		int[][] visited = new int[500001][2];
		for (int[] row : visited) Arrays.fill(row, -1);
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {n,0});
		visited[n][0] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int loc = cur[0], t = cur[1];
			
			for (int next : new int[] {loc-1, loc+1, loc*2}) {
				if (next < 0 || next > 500000 || visited[next][(t+1)%2] != -1) continue;
				q.add(new int[] {next,t+1});
				visited[next][(t+1)%2] = t + 1;
			}
		}
		
		boolean flag = false;
		for (int i = 1;; i++) {
			k += i;
			if (k > 500000) break;
			if (visited[k][i%2] <= i && visited[k][i%2] != -1) {
				System.out.println(i);
				flag = true;
				break;
			}
		}
		
		if (!flag) System.out.println(-1);
	}

}