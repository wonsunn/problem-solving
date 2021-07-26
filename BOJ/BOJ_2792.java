package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_2792 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] color = new int[m];
		
		int left = 1, right = 0;
		for (int i = 0; i < m; i++) {
			color[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, color[i]);
		}
		
		int res = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			long sum = 0;
			for (int i = 0; i < m; i++) {
				sum += (color[i] % mid == 0) ? color[i] / mid : color[i] / mid + 1;
			}
			
			if (sum > n)
				left = mid + 1;
			else {
				res = mid;
				right = mid - 1;
			}
		}
		
		System.out.println(res);
	}

}
