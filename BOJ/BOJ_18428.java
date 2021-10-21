package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_18428 {
	
	static int n;
	static boolean flag = false;
	static char[][] map;
	static List<int[]> teachers = new ArrayList<>();
	static int[] select = new int[3];
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T')
					teachers.add(new int[] {i,j});
			}
		}
		
		DFS(0, 0);
		if (!flag) System.out.println("NO");
	}
	
	static boolean isPossible() {
		char[][] copyMap = new char[n][n];
		copyArr(copyMap, map);
		for (int i = 0; i < 3; i++) {
			int x = select[i] / n;
			int y = select[i] % n;
			copyMap[x][y] = 'O';
		}
		
		for (int[] a : teachers) {
			int x = a[0], y = a[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				while (true) {
					if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
					
					if (copyMap[nx][ny] == 'S') return false;
					else if (copyMap[nx][ny] == 'O') break;
					
					nx += dx[i];
					ny += dy[i];
				}
			}
		}
		
		return true;
	}
	
	static void DFS(int level, int cur) {
		if (flag) return;
		
		if (level == 3) {
			if (isPossible()) {
				System.out.println("YES");
				flag = true;
			}
			return;
		}
		
		for (int i = cur; i < n*n; i++) {
			int x = i / n;
			int y = i % n;
			if (map[x][y] == 'X') {
				select[level] = i;
				DFS(level+1, i+1);
			}
		}
	}
	
	static void copyArr(char[][] A, char[][] B) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = B[i][j];
			}
		}
	}

}