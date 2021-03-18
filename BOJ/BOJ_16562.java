import java.io.*;
import java.util.*;

public class BOJ_16562 {

	static int n, m, k;
	static int[] parents, cost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		parents = new int[n + 1];
		cost = new int[n + 1];
		for (int i = 1; i <= n; i++) parents[i] = i;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i<= n; i++) cost[i] = Integer.parseInt(st.nextToken());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			union(v, w);
		}
		
		for (int i = 1; i <= n; i++) {
			int tmp = find(i);
			if (parents[tmp] != 0) {
				sum += cost[tmp];
				union(0, tmp);
			}
		}
		
		if (sum > k) 
			System.out.println("Oh no");
		else
			System.out.println(sum);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (cost[a] < cost[b])
			parents[b] = a;
		else
			parents[a] = b;
	}
	
	static int find(int a) {
		if (a == parents[a]) return a;
		else return parents[a] = find(parents[a]);
	}

}