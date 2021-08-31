package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_21610 {

	static int n, m;
	static int[][] board;
	static LinkedList<Integer> clouds = new LinkedList<>();
	
	static int[] dx = {0, 0, -1, -1, -1, 0, 1 ,1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 구름 위치 넣기
		for (int i = n-1; i <= n; i++) {
			for (int j = 1; j <= 2; j++) 
				clouds.add(i*n+j);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			moveCloud(dir, num);
			copyWater();
			makeCloud();
		}
		
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sum += board[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	static void moveCloud(int dir, int num) {
		int size = clouds.size();
		for (int i = 0; i < size; i++) {
			int loc = clouds.poll();
			int x = loc % n == 0 ? loc / n - 1 : loc / n;
			int y = loc % n == 0 ? n : loc % n;
			for (int j = 0; j < num % n; j++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if (nx == 0) nx = n;
				else if (nx == n+1) nx = 1;
				if (ny == 0) ny = n;
				else if (ny == n+1) ny = 1;
				
				x = nx; y = ny;
			}
			clouds.add(x*n+y);
			board[x][y] += 1;
		}
	}
	
	static void copyWater() {
		for (int loc : clouds) {
			int x = loc % n == 0 ? loc / n - 1 : loc / n;
			int y = loc % n == 0 ? n : loc % n;
			int cnt = 0;
			for (int i = 2; i <= 8; i += 2) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 1 || nx > n || ny < 1 || ny > n || board[nx][ny] == 0) continue;
				cnt++;
			}
			board[x][y] += cnt;
		}
	}
	
	static void makeCloud() {
		LinkedList<Integer> tmpCloud = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (!clouds.contains(i*n+j) && board[i][j] >= 2) {
					board[i][j] -= 2;
					tmpCloud.add(i*n+j);
				}
			}
		}
		clouds.clear();
		clouds = new LinkedList<>(tmpCloud);
	}

}
