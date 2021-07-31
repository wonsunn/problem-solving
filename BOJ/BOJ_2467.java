package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_2467 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = n-1, min = Integer.MAX_VALUE;
		int minVal = 0, maxVal = 0;
		while (left < right) {
			if (Math.abs(arr[left] + arr[right]) < min) {
				min = Math.abs(arr[left] + arr[right]);
				minVal = arr[left]; maxVal = arr[right];
			}
			int val = arr[left] + arr[right];
			if (val <= 0) left++;
			else right--;
		}
		
		System.out.println(minVal + " " + maxVal);
	}

}
