package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_15900 {

	static int n;
	static long res;
	static boolean[] visited;
	static List<Integer>[] nodes;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		nodes = new ArrayList[n+1];
		for (int i = 1; i <= n; i++)
			nodes[i] = new ArrayList<>();
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}
		
		DFS(1, 0);
		System.out.println(res % 2 == 0 ? "No" : "Yes");
	}
	
	static void DFS(int root, long dis) {
		visited[root] = true;
		
		boolean flag = false;
		for (int next : nodes[root]) {
			if (!visited[next]) {
				flag = true;
				DFS(next, dis+1);
			}
		}
		
		if (!flag) {
			res += dis;
		}
	}

}
