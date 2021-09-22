package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_1027 {
	
	static class Info {
		int idx;
		double lean;
		
		Info (int idx, double lean) {
			this.idx = idx;
			this.lean = lean;
		}
	}
	
	static int n;
	static int[] height;
	static Stack<Info> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		height = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int left = 0, right = 0;
			stack = new Stack<>();
			for (int j = i-1; j >= 1; j--) {
				solve(i, j, 0);
			}
			if (!stack.isEmpty()) left = stack.size();
			
			stack = new Stack<>();
			for (int j = i+1; j <= n; j++) {
				solve(i, j, 1);
			}
			if (!stack.isEmpty()) right = stack.size();
			
			max = Math.max(max, left+right);
		}
		
		System.out.println(max);
	}
	
	static void solve(int i, int j, int type) {
		double curLean = 1.0 * (height[j] - height[i]) / (j - i);
		if (stack.isEmpty()) stack.add(new Info(j,curLean));
		else {
			if ((type == 0 && curLean < stack.peek().lean) || (type == 1 && curLean > stack.peek().lean))
				stack.add(new Info(j, curLean));
		}
	}

}
