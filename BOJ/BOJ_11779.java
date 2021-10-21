package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_11779 {
	
	static final int MAX = 987654321;
	static int n, m, start, end;
	static List<Node>[] graph;
	static int[] dis, route;
	static boolean[] visited;
	
	static class Node implements Comparable<Node> {
		int node, cost;
		
		Node (int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		
		public int compareTo(Node n) {
			return this.cost - n.cost;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		dis = new int[n+1];
		route = new int[n+1];
		visited = new boolean[n+1];
		graph = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			dis[i] = MAX;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		
		List<Integer> routes = new ArrayList<>();
		int tmp = end;
		while (tmp != 0) {
			routes.add(tmp);
			tmp = route[tmp];
		}
		
		sb.append(dis[end]).append('\n');
		sb.append(routes.size()).append('\n');
		for (int i = routes.size() - 1; i >= 0; i--)
			sb.append(routes.get(i)).append(' ');
		System.out.println(sb.toString());
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dis[start] = 0;
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			int curNode = pq.peek().node;
			pq.poll();
			
			if (!visited[curNode]) visited[curNode] = true;
			else continue;
			
			for (Node next : graph[curNode]) {
				int nextNode = next.node;
				int nextCost = next.cost;
				
				if (dis[curNode] + nextCost < dis[nextNode]) {
					dis[nextNode] = dis[curNode] + nextCost;
					route[nextNode] = curNode;
					pq.add(new Node(nextNode, dis[nextNode]));
				}
			}
		}
	}

}
