package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_7662 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				if (type.equals("I")) {
					map.put(num, map.getOrDefault(num, 0) + 1);
				}
				else {
					if (map.isEmpty()) continue;
					
					if (num == 1) {
						int maxVal = map.lastKey();
						int next = map.get(maxVal) - 1;
						if (next == 0) map.remove(maxVal);
						else map.put(maxVal, next);
					} else {
						int minVal = map.firstKey();
						int next = map.get(minVal) - 1;
						if (next == 0) map.remove(minVal);
						else map.put(minVal, next);
					}
				}
			}
			
			if (map.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(map.lastKey() + " " + map.firstKey() + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
