package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_1766 {
	
	static int n;
	static int[] inDegree;
	static List<Integer>[] routes;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		inDegree = new int[n+1];
		routes = new ArrayList[n+1];
		for (int i = 1; i <= n; i++)
			routes[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			inDegree[b]++;
			routes[a].add(b);
		}
		
		for (int i = 1; i <= n; i++) {
			if (inDegree[i] == 0)
				pq.add(i);
		}
		
		System.out.println(solve());
	}
	
	static String solve() {
		StringBuilder sb = new StringBuilder();
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			sb.append(cur).append(' ');
			
			for (int next : routes[cur]) {
				if (--inDegree[next] == 0) {
					pq.add(next);
				}
			}
		}
		
		return sb.toString();
	}

}
