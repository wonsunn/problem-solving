package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_1421 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int max = 0;
		int[] height = new int[n];
		
		for (int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(br.readLine()); 
			max = Math.max(max, height[i]);
		}
		
		long res = 0;
		for (int i = max; i > 0; i--) {
			long sum = 0;
			for (int j = 0; j < n; j++) {
				// 자르는 횟수 : i 높이로 나누어 떨어지면 (몫 - 1)번 만에 나눠진다. ex) 높이 2를 1로 만드려고 하면 1번만에 1 두개로 만듦 
				int cnt = (height[j] % i == 0) ? height[j] / i - 1 : height[j] / i; 
				// 수익 : (잘라서 얻은 나무의 개수 * 높이 * 단위) - (자르는 횟수 * 자르는 비용)
				int profit = ((height[j] / i) * i * w) - (cnt * c); 
				// 수익이 자르는 비용으로 인해 음수가 될 경우에는 아예 나무를 팔지 않는 경우가 나으므로 수익이 양수일 때만 합산한다.
				if (profit > 0) {
					sum += ((height[j] / i) * i * w) - (cnt * c);
				}
			}
			
			res = Math.max(res, sum);
		}
		
		System.out.println(res);	
	}

}
