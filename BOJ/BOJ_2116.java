import java.io.*;
import java.util.*;

public class BOJ_2116 {

	static final int NUM = 6;
	static int n;
	static int[][] dice;
	static boolean[][] info;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dice = new int[n][NUM];
        info = new boolean[n][NUM];
        int[] arr = {5, 3, 4, 1, 2, 0};
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < NUM; j++)
        		dice[i][j] = Integer.parseInt(st.nextToken());
        }
        
        int res = -1;
        for (int i = 0; i < NUM; i++) {
        	initialize();
        	int up = dice[0][arr[i]];
        	info[0][i] = true;
        	info[0][arr[i]] = true;
        	int idx = 1;
        	
        	while (true) {
        		for (int j = 0; j < NUM; j++) {
        			if (dice[idx][j] == up) {
        				up = dice[idx][arr[j]];
        				info[idx][j] = true;
        				info[idx][arr[j]] = true;
        				break;
        			}
        		}
        		
        		idx++;
        		
        		if (idx == n) break;
        	}
        	
        	res = Math.max(res, calculate());
        }
        
        System.out.println(res);
	}
	
	static int calculate() {
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int max = -1;
			for (int j = 0; j < NUM; j++) {
				if (!info[i][j])
					max = Math.max(max, dice[i][j]);
			}
			sum += max;
		}
		
		return sum;
	}
	
	static void initialize() {
		for (boolean[] row : info)
			Arrays.fill(row, false);
	}

}