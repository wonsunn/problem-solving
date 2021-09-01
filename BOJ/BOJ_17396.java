package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_17396 {

	static int n, m;
	static long[] dis;
	static int[] chk;
	static List<Info>[] graph;
	
	static class Info implements Comparable<Info> {
		int node;
		long cost;
		
		Info (int node, long cost) {
			this.node = node;
			this.cost = cost;
		}

		public int compareTo(Info o) {
			if (this.cost - o.cost > 0) return 1;
			else return -1;
		}
	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		chk = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			chk[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList[n];
		for (int i = 0; i < n; i++)
			graph[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new Info(b, c));
			graph[b].add(new Info(a, c));
		}
		
		dis = new long[n];
		Arrays.fill(dis, Long.MAX_VALUE);
		dis[0] = 0;
		
		System.out.println(dijkstra());
	}
	
	static long dijkstra() {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.add(new Info(0,0));
		
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			
			if (cur.node == n-1) return dis[cur.node];
			if (chk[cur.node] == 1) continue;
			
			for (int i = 0; i < graph[cur.node].size(); i++) {
				Info next = graph[cur.node].get(i);
				
				if (dis[cur.node] + next.cost < dis[next.node]) {
					dis[next.node] = dis[cur.node] + next.cost;
					pq.add(new Info(next.node, dis[next.node]));
				}
			}
		}
		
		return -1;
	}

}
