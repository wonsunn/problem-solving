package com.ssafy.algo;

import java.io.*;
import java.util.*;

public class BOJ_2922 {

	static String mouem = "AEIOU";
	static long res;
	static char[] word;
	static int[] wordVal; // 빈칸 : 0, 모음 : 1, 자음 : 2
	static List<Integer> lines = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		word = br.readLine().toCharArray();
		wordVal = new int[word.length];
		
		boolean existL = false;
		for (int i = 0; i < word.length; i++) {
			if (mouem.indexOf(word[i]) == -1) {
				if (word[i] == '_') lines.add(i);
				else wordVal[i] = 2;
			} else {
				wordVal[i] = 1;
			}
			
			if (word[i] == 'L') existL = true;
		}
		
		DFS(0, 1, existL);
		System.out.println(res);
	}
	
	static void DFS(int level, long num, boolean existL) {
		if (level == lines.size()) {
			if (existL && checkWord()) {
				res += num;
			}
			return;
		}
		
		int idx = lines.get(level);
		// 모음	
		wordVal[idx] = 1;
		DFS(level+1, num * 5, existL);
		
		// L
		wordVal[idx] = 2;
		DFS(level+1, num, true);
		
		// L 이외 자음
		wordVal[idx] = 2;
		DFS(level+1, num * 20, existL);
	}
	
	static boolean checkWord() {
		for (int i = 2; i < wordVal.length; i++) {
			if (wordVal[i-2] == wordVal[i-1] && wordVal[i-1] == wordVal[i])
				return false;
		}
		return true;
	}

}
