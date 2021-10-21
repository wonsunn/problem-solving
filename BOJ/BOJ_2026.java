package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_2026 {

	static int k, n, f;
	static boolean flag = false;
	
	static boolean[][] friends;
	static int[] count;
	static boolean[] sel;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		f = Integer.parseInt(st.nextToken());
		friends = new boolean[n+1][n+1];
		count = new int[n+1];
		sel = new boolean[n+1];
		
		for (int i = 0; i < f; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friends[a][b] = friends[b][a] = true;
			count[a]++; count[b]++;
		}
		
		for (int i = 1; i <= n; i++) {
			if (count[i] < k-1) continue;
			if (flag) break;
			
			sel[i] = true;
			DFS(1, i);
			sel[i] = false;	
		}
		
		if (!flag) System.out.println(-1);
	}
	
	static void DFS(int level, int cur) {
		if (flag) return;
		if (level == k) {
			for (int i = 1; i <= n; i++) {
				if (sel[i] == true) System.out.println(i);
			}
			flag = true;
			return;
		}
		
		for (int i = cur+1; i <= n; i++) {
			if (!friends[cur][i] || !isPossible(i)) continue;
			
			sel[i] = true;
			DFS(level+1, i);
			sel[i] = false;
		}
	}
	
	static boolean isPossible(int cur) {
		for (int i = 1; i <= n; i++) {
			if (sel[i] && !friends[cur][i]) {
				return false;
			}
		}
		return true;
	}

}
