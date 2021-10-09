package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_1593 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int g = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[][] count = new int[2][58];
		
		String W = br.readLine();
		for (int i = 0; i < g; i++) count[0][W.charAt(i) - 'A']++;
		
		String S = br.readLine();
		int left = 0, right = g-1;
		for (int i = left; i <= right; i++) count[1][S.charAt(i) - 'A']++;
		
		int cnt = 0;
		while (true) {
			boolean flag = true;
			for (int i = 0; i < 58; i++) {
				if (count[0][i] != count[1][i]) {
					flag = false;
					break;
				}
			}
			
			if (flag) cnt++;
			
			count[1][S.charAt(left) - 'A']--;
			left++;
			right++;
			if (right >= s) break;
			count[1][S.charAt(right) - 'A']++;
		}
		
		System.out.println(cnt);
	}
	
}
