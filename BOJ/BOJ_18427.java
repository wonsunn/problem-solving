package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_18427 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n+1][h+1];
		List<Integer>[] blocks = new ArrayList[n+1];
		for (int i = 1; i <= n; i++)
			blocks[i] = new ArrayList<>();
		
		for (int i = 1; i <= n; i++) {
			blocks[i].add(0);
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				blocks[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int b : blocks[1])
			dp[1][b] = 1;
		
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j <= h; j++) {
				if (dp[i-1][j] == 0) continue;
				for (int b : blocks[i]) {
					if (j + b <= h) {
						dp[i][j+b] += dp[i-1][j];
						dp[i][j+b] %= 10007;
					}
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= h; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(dp[n][h] % 10007);
		
	}

}
