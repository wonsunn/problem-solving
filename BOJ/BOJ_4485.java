package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_4485 {

	static final int MAX = 987654321;
	static int n;
	static int[][] board, dis;
	static PriorityQueue<Node> pq;
	
	static class Node implements Comparable<Node> {
		int x, y, val;
		
		Node (int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Node n) {
			return this.val - n.val;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		
		int idx = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			dis = new int[n][n];
			board = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					dis[i][j] = MAX;
				}
			}
			dis[0][0] = board[0][0];
			
			dijkstra();
			sb.append("Problem ").append(idx++).append(": ").append(dis[n-1][n-1]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra() {
		pq = new PriorityQueue<>();
		pq.add(new Node(0,0,dis[0][0]));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = cur.x, y = cur.y, val = cur.val;
		
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if (val + board[nx][ny] < dis[nx][ny]) {
					dis[nx][ny] = val + board[nx][ny];
					pq.add(new Node(nx,ny,dis[nx][ny]));
				}
			}
		}
	}

}
