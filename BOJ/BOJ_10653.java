package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_10653 {

	static int n, k, min = Integer.MAX_VALUE;
	static int[][] loc, dp, dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		loc = new int[n+1][2];
		dist = new int[n+1][n+1];
		dp = new int[n+1][n+1];
		for (int[] row : dp) Arrays.fill(row, -1);
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			loc[i][0] = Integer.parseInt(st.nextToken());
			loc[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = i+1; j <= n; j++) {
				dist[i][j] = Math.abs(loc[i][0] - loc[j][0]) + Math.abs(loc[i][1] - loc[j][1]);
			}
		}
		
		System.out.println(DFS(n, k));
	}
	
	static int DFS(int n, int k) {
		if (dp[n][k] != -1) return dp[n][k];
		if (n == 1) return 0;
		
		int res = Integer.MAX_VALUE;
		
		for (int i = 0; i <= k; i++) {
			if (n-i-1 < 0) break;
			res = Math.min(DFS(n-i-1, k-i) + dist[n-i-1][n], res);
		}
		
		return dp[n][k] = res;
	}

}
