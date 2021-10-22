package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_6236 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] cost = new int[n];
		int sum = 0, left = 0, right = 0;
		for (int i = 0; i < n; i++) {
			cost[i] = Integer.parseInt(br.readLine());
			left = Math.max(left, cost[i]);
			sum += cost[i];
		}
		
		right = sum; 
		
		int mid = 0;
		while(left <= right) {
			mid = (left + right) / 2;
			int money = mid;
			int cnt = 1;
			
			for (int i = 0; i < n; i++) {
				money -= cost[i];
				
				if (money <= 0) {
					money = mid - cost[i];
					cnt++;
				}
			}

			if (cnt > m) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(mid);
	}

}
