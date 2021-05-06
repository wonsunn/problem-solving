package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_20366 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
        
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
        
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < n-3; i++) {
			for (int j = i+3; j < n; j++) {
				int left = i + 1;
				int right = j - 1;
				while (left < right) {
					int val = (arr[left] + arr[right]) - (arr[i] + arr[j]);
					res = Math.min(res, Math.abs(val));
					if (res == 0) {
						System.out.println(res);
						return;
					}
					if (val > 0) 
						right -= 1;
					else if (val < 0)
						left += 1;
				}
			}
		}
        
		System.out.println(res);
	}

}
