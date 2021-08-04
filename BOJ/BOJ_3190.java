package com.ssafy.study;

import java.io.*;
import java.util.*;

public class BOJ_3190 {

	static LinkedList<int[]> q = new LinkedList<>();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		boolean[][] isApple = new boolean[n+1][n+1];
		boolean[][] snake = new boolean[n+1][n+1];
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			isApple[x][y] = true;
		}
		int l = Integer.parseInt(br.readLine());
		char[] dir = new char[10001];
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			dir[num] = d;
		}
		
		int time = 0, d = 0, x = 1, y = 1;
		q.add(new int[] {1,1});
		snake[1][1] = true;
		
		while (true) {
			if (dir[time] == 'D') d = (d+1) % 4;
			if (dir[time] == 'L') d = (d+3) % 4;
			
			time++;
			
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 1 || nx > n || ny < 1 || ny > n || snake[nx][ny]) {
				System.out.println(time);
				break;
			}
			
			snake[nx][ny] = true;
			q.addFirst(new int[] {nx,ny});
			if (!isApple[nx][ny]) {
				int[] tail = q.pollLast();
				snake[tail[0]][tail[1]] = false;
			} else {
				isApple[nx][ny] = false;
			}
			
			x = nx; y = ny;
		}	
	}

}
