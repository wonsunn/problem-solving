package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_14500 {

	static int n, m, res;
	static int[][] board;
	static boolean[][] visited;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = true;
				DFS(0, i, j, board[i][j]);
				visited[i][j] = false;
				checkCross(i, j);
			}
		}
		
		System.out.println(res);
	}
	
	static void checkCross(int x, int y) {
		int wsum = 0, hsum = 0;
		int tmp1 = 0, tmp2 = 0;
		
		if (m - y >= 3) {
			for (int i = y; i < y+3; i++) {
				wsum += board[x][i];
			}
			
			if (x-1 >= 0 && x-1 < n) tmp1 = board[x-1][y+1];
			if (x+1 >= 0 && x+1 < n) tmp2 = board[x+1][y+1];
			wsum = Math.max(wsum+tmp1, wsum+tmp2);
		}
		
		if (n - x >= 3) {
			for (int i = x; i < x+3; i++) {
				hsum += board[i][y];
			}
			
			if (y-1 >= 0 && y-1 < m) tmp1 = board[x+1][y-1];
			if (y+1 >= 0 && y+1 < m) tmp2 = board[x+1][y+1];
			hsum = Math.max(hsum+tmp1, hsum+tmp2);
		}
		
		res = Math.max(res, Math.max(wsum, hsum));
	}
	
	static void DFS(int level, int x, int y, int sum) {
		if (level == 3) {
			res = Math.max(res, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			DFS(level+1, nx, ny, sum + board[nx][ny]);
			visited[nx][ny] = false;
		}
	}

}
