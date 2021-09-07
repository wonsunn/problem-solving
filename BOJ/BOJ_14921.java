package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_14921 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = n-1, min = Integer.MAX_VALUE, res = 0;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				res = sum;
			}
			
			if (sum < 0) left++;
			else if (sum > 0) right--;
			else {
				System.out.println(sum);
				return;
			}
		}
		
		System.out.println(res);
	}

}
