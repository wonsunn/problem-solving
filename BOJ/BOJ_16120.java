package com.ssafy.algo;

import java.io.*;

public class BOJ_16120 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int cnt = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'P') {
				cnt++;
				continue;
			}
			
			if (cnt >= 2 && (i < str.length()-1 && str.charAt(i+1) == 'P')) {
				cnt--;
				i++;
			} else {
				System.out.println("NP");
				return;
			}
		}
		
		if (cnt == 1) System.out.println("PPAP");
		else System.out.println("NP");	
	}

}
