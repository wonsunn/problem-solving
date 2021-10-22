package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_1253 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(num);
		
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			int target = num[i];
			int left = 0, right = n-1, sum = 0;
			
			while (left < right) {
				sum = num[left] + num[right];
				
				if (target == sum) {
					if (i != left && i != right) {
						cnt++;
						break;
					}
					
					if (i == left) left++;
					if (i == right) right--;
				}
				else if (target < sum) right--;
				else left++;
			}
		}
		
		System.out.println(cnt);
	}

}
