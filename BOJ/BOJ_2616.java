package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_2616 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] sum = new int[n+1];
		int[][] dp = new int[4][n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int cur = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + cur;
		}
		int limit = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < 4; i++) {
			for (int j = i * limit; j <= n; j++) {
				dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-limit] + sum[j] - sum[j-limit]);
			}
		}
		
		System.out.println(dp[3][n]);
	}

}
