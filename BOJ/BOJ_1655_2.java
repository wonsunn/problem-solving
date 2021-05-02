package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_1655 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		
		int first = Integer.parseInt(br.readLine());
		list.add(first);
		sb.append(first).append('\n');
		int c_idx = 0; // 가운데 위치
		
		for (int i = 1; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			int left = 0, right = list.size() - 1, new_idx = 0;
			while (left < right) {
				int mid = (left + right) / 2;
				int mid_num = list.get(mid);
				
				if (mid_num <= num) left = mid + 1;
				else right = mid - 1;
			}
			
			if (num >= list.get(left)) new_idx = left + 1;
			else new_idx = left;
			
			if (list.size() % 2 == 0) c_idx += 1;
			
			list.add(new_idx, num);			
			sb.append(list.get(c_idx)).append('\n');
		}
		
		System.out.println(sb);
	}

}
