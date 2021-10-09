package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_10814 {
	
	static class Info implements Comparable<Info> {
		int order, age;
		String name;
		
		Info(int order, int age, String name) {
			this.order = order;
			this.age = age;
			this.name = name;
		}
		
		public int compareTo(Info a) {
			if (this.age == a.age) return this.order - a.order;
			else return this.age - a.age;
		}

	}
	
	static PriorityQueue<Info> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String na = st.nextToken();
			pq.add(new Info(i, a, na));
		}
		
		while (!pq.isEmpty()) {
			sb.append(pq.peek().age).append(" ").append(pq.peek().name).append('\n');
			pq.poll();
		}
		System.out.println(sb.toString());
	}

}
