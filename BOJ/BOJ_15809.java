package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_15809 {
	
	static int n, m;
	static int[] parents, people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parents = new int[n+1];
		people = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
			people[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int opt = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			
			union(opt, p, q);
		}
		
		List<Integer> list = new ArrayList<>();
		boolean[] isLive = new boolean[n+1];
		for (int i = 1; i <= n; i++) {
			int pa = find(i);
			if (!isLive[pa] && people[pa] != 0) {
				isLive[pa] = true;	
				list.add(people[pa]);
			}
		}
		Collections.sort(list);
	
		sb.append(list.size()).append('\n');
		for (int i : list) {
			sb.append(i).append(' ');
		}
		
		System.out.println(sb.toString());
	}
	
	static void union(int opt, int p, int q) {
		p = find(p);
		q = find(q);
		
		if (p == q) return;
		
		if (opt == 1) {
			if (p < q) {
				parents[q] = p;
				people[p] += people[q];
			} else {
				parents[p] = q;
				people[q] += people[p];
			}	
		} else {
			if (people[p] < people[q]) {
				parents[p] = q;
				people[q] -= people[p];
			} else if (people[p] > people[q]) {
				parents[q] = p;
				people[p] -= people[q];
			} else {
				people[p] = people[q] = 0;
			}
		}
	}
	
	static int find(int p) {
		if (p == parents[p]) return p;
		else return parents[p] = find(parents[p]);
	}

}
