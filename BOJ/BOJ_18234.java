package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_18234 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		List<long[]> carrots = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long w = Long.parseLong(st.nextToken());
			long p = Long.parseLong(st.nextToken());
			carrots.add(new long[] {w,p});
		}
		
		Collections.sort(carrots, (c1, c2) -> Long.compare(c1[1], c2[1]));
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += carrots.get(i)[0] + carrots.get(i)[1] * (i + t - n);
		}
		
		System.out.println(sum);
	}

}
