package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_14719 {
	
	static int h, w;
	static int[] height;
	static Stack<Integer> stack = new Stack<>();
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
//		height = new int[w];
		
		int sum = 0, max_height= 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < w; i++) {
			int height = Integer.parseInt(st.nextToken());
			if (stack.isEmpty()) {
				stack.push(height);
				max_height = height;
			}
			else {
				int cur_height = Math.min(max_height, height);
				while (stack.peek() < cur_height) {
					q.add(stack.pop());
				}
				
				while (!q.isEmpty()) {
					sum += cur_height - q.poll(); // 채워지는 빗물 양만큼 더하기
					stack.push(cur_height);
				}
				
				stack.push(height); // 원래 넣어야 할 높이 넣기
			}
			
			max_height = Math.max(max_height, height); // 최대 높이 갱신 
		}
		
		System.out.println(sum);
	}

}
