package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_1012 {
	
	static int m, n;
	static boolean[][] board, visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			board = new boolean[n][m];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				board[x][y] = true;
			}
						
			int cnt = 0;
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (!visited[i][j] && board[i][j]) {
						BFS(i, j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
	}
	
	static void BFS(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (nx < 0 | nx >= n || ny < 0 || ny >= m || visited[nx][ny] || !board[nx][ny]) continue;
				
				q.add(new int[] {nx,ny});
				visited[nx][ny] = true;
			}
		}
	}

}
