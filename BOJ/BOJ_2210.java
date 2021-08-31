package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_2210 {

	static final int N = 5;
	static int[][] board;
	static int cnt;
	static Set<String> set = new HashSet<>();
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				DFS(i, j, String.valueOf(board[i][j]), 0);
			}
		}
		
		System.out.println(cnt);
	}
	
	static void DFS(int x, int y, String str, int level) {
		if (level == N) {
			if (!set.contains(str)) {
				cnt++;
				set.add(str);
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			DFS(nx, ny, str.concat(String.valueOf(board[nx][ny])), level+1);
		}
		
	}

}
