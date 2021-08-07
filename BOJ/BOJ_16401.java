package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_16401 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] height = new int[n];
		
		int left = 1, right = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			right = Math.max(right, height[i]);
		}
		
		int res = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			long sum = 0;
			for (int i = 0; i < n; i++) {
				sum += height[i] / mid;
			}
			
			if (sum >= m) {
				res = mid;
				left = mid + 1;
			}
			else { 
				right = mid - 1;
			}
		}
		
		System.out.println(res);
	}

}
