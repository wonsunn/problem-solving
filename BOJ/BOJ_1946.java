import java.io.*;
import java.util.*;

public class BOJ_1946 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
        	int min = Integer.MAX_VALUE, fail = 0;
        	int n = Integer.parseInt(br.readLine());
        	int[] rank = new int[n + 1];
        	
        	for (int i = 0; i < n; i++) {
        		st = new StringTokenizer(br.readLine());
        		int score1 = Integer.parseInt(st.nextToken());
        		int score2 = Integer.parseInt(st.nextToken());
        		rank[score1] = score2;
        	}
        	
        	for (int i = 1; i <= n; i++) {
        		if (rank[i] > min) fail++;
        		else min = rank[i];
        	}
        	
        	System.out.println(n - fail);
        }
	}
}
