package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_4811 {
	
	static long[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			
			dp = new long[n+1][n+1];
			
			System.out.println(DFS(n, 0));
		}

	}
	
	static long DFS(int w, int h) {
		if (w == 0 && h == 0) return 1;
		if (w < 0 || h < 0) return 0;
		
		if (dp[w][h] != 0) return dp[w][h];
		
		return dp[w][h] = DFS(w-1, h+1) + DFS(w, h-1);
	}

}
