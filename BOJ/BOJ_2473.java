package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_2473 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(arr);
		
		int s = 0, m = 0, e = 0; // 최종 세 용액을 저장하기 위한 변수
		long min = Long.MAX_VALUE; // 전체 for문 돌아가면서 저장되는 최솟값
		// i가 left, j가 right	
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 2; j < n; j++) {
				long sum = arr[i] + arr[j];
				long three = 0; // 세 용액의 합
				int middle = 0; // 가운데 값
				// 둘 다 음수일 경우, j-1이 중간 값일 때 최솟값이 됨
				if (arr[i] < 0 && arr[j] < 0) {
					middle = j-1;
					three = Math.abs(sum + arr[j-1]);
				}
				// 둘 다 양수일 경우, i+1이 중간 값일 때 최솟값이 됨
				else if (arr[i] > 0 && arr[j] > 0) {
					middle = i+1;
					three = sum + arr[i+1];
				}
				// left가 음수, right가 양수일 경우, 이분탐색	
				else {
					int left = i+1, right = j-1;	
					long inner_min = Long.MAX_VALUE; // 세 용액 합의 최솟값
					while (left <= right) {
						int mid = (left + right) / 2;
						long val = sum + arr[mid];
						
						// 합의 최솟값이 갱신될 때, 값이랑 그 때 중간 위치 저장
						if (Math.abs(val) <= inner_min) {
							inner_min = Math.abs(val);
							middle = mid;
						}
						
						// 음수이면 더 큰 값 탐색
						if (val < 0) left = mid + 1;
						// 양수이면 더 작은 값 탐색
						else right = mid - 1;
					}
					
					three = inner_min; // 최종으로 도출된 최솟값을 전체 최솟값에 저장
				}
				
				// 최솟값 갱신
				if (three < min) {
					min = three;
					s = arr[i]; m = arr[middle]; e = arr[j];
				}	
			}
		}
		
		System.out.println(s + " " + m + " " + e);
	}
	
}
