package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_18116 {
	
	static final int MAX = 1000000;
	static int[] parents = new int[MAX+1];
	static int[] count = new int[MAX+1];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= MAX; i++) {
			parents[i] = i;
			count[i] = 1;
		}
		
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String opt = st.nextToken();
			if (opt.equals("I")) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			} else {
				int a = Integer.parseInt(st.nextToken());
				a = find(a);
				sb.append(count[a]).append('\n');
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return;
		
		count[a] += count[b];
		parents[parents[b]] = a;
	}

	static int find(int a) {
		if (a == parents[a]) return a;
		else return parents[a] = find(parents[a]);
	}
		
}
