package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_5568 {

	static int n, k, cnt;
	static int[] card;
	static boolean[] visited;
	static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		card = new int[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			card[i] = Integer.parseInt(br.readLine());
		}
		
		perm(0, "");
		System.out.println(cnt);
	}
	
	static void perm(int level, String str) {
		if (level == k) {
			if (!set.contains(str)) {
				cnt++;
				set.add(str);
			}
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				perm(level+1, str.concat(String.valueOf(card[i])));
				visited[i] = false;
			}
		}
	}

}
