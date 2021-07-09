package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_21608 {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node implements Comparable<Node> {
		int x, y, like_cnt, empty_cnt;
		
		Node (int x, int y, int like_cnt, int empty_cnt) {
			this.x = x;
			this.y = y;
			this.like_cnt = like_cnt;
			this.empty_cnt = empty_cnt;
		}

		@Override
		public int compareTo(Node o) {
			if (this.like_cnt == o.like_cnt) {
				if (this.empty_cnt == o.empty_cnt) {
					if (this.x == o.x) 
						return this.y - o.y;
					else 
						return this.x - o.x;
				} else 
					return o.empty_cnt - this.empty_cnt;
			} else
				return o.like_cnt - this.like_cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		List<Integer>[] likes = new ArrayList[n*n+1];
		for (int i = 1; i <= n*n; i++)
			likes[i] = new ArrayList<>();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for (int i = 0; i < n*n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cur_idx = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				likes[cur_idx].add(Integer.parseInt(st.nextToken()));
			}
			
			pq.clear();
			
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (board[x][y] != 0) continue;
					
					int empty_cnt = 0, like_cnt = 0;
					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
						
						if (likes[cur_idx].contains(board[nx][ny])) like_cnt++;
						if (board[nx][ny] == 0) empty_cnt++;
					}
					
					pq.add(new Node(x, y, like_cnt, empty_cnt));
				}
			}
			
			int newX = pq.peek().x;
			int newY = pq.peek().y;
			board[newX][newY] = cur_idx;
		}
		
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int idx = board[i][j];
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
					
					if (likes[idx].contains(board[nx][ny])) cnt++;
				}
				sum += (int)Math.pow(10, cnt-1);
			}
		}
		
		System.out.println(sum);
	}

}
