package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_14621 {

	static int n, w;
	static int[] parents;
	static char[] types;
	static PriorityQueue<Info> pq = new PriorityQueue<>();
	static class Info implements Comparable<Info> {
		int s1, s2, dist;
		
		Info (int s1, int s2, int dist) {
			this.s1 = s1;
			this.s2 = s2;
			this.dist = dist;
		}
		
		public int compareTo(Info o) {
			return this.dist - o.dist;
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		types = new char[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			types[i] = st.nextToken().charAt(0);
			parents[i] = i;
		}
		for (int i = 0; i < w; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			pq.add(new Info(s1, s2, d));
		}
		
		int sum = 0, cnt = 0;
		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			if (types[cur.s1] == types[cur.s2] || isSameParents(cur.s1, cur.s2)) {
				continue;
			}
			
			union(cur.s1, cur.s2);
			cnt++;
			sum += cur.dist;
		}
		
		if (cnt == n-1) System.out.println(sum);
		else System.out.println(-1);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
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

}
