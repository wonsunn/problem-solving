package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_1967 {

	static int res, fNode;
	static List<int[]>[] node;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		node = new ArrayList[n+1];
		for (int i = 1; i <= n; i++)
			node[i] = new ArrayList<>();
		
		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			node[p].add(new int[] {c,w});
			node[c].add(new int[] {p,w});
		}
		
		DFS(1, 0, -1);
		res = 0;
		DFS(fNode, 0, -1);
		
		System.out.println(res);
	}
	
	static void DFS(int n, int weight, int prev) {
		if (res < weight) {
			res = weight;
			fNode = n;
		}
		
		for (int i = 0; i < node[n].size(); i++) {
			int next = node[n].get(i)[0];
			int cost = node[n].get(i)[1];
			if (next != prev) {
				DFS(next, weight+cost, n);
			}
		}
	}

}
