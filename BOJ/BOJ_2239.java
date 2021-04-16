import java.io.*;
import java.util.*;

public class BOJ_2239 {
	
	static final int SIZE = 81;
	static final int NUM = 9;
	
	static int[][] puzzle = new int[NUM][NUM];
	
	static boolean[][] row = new boolean[NUM][NUM+1];
	static boolean[][] col = new boolean[NUM][NUM+1];
	static boolean[][] area = new boolean[NUM][NUM+1];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int i = 0; i < NUM; i++) {
        	String tmp = br.readLine();
        	for (int j = 0; j < NUM; j++) {
        		puzzle[i][j] = Integer.parseInt(String.valueOf(tmp.charAt(j)));
        		if (puzzle[i][j] != 0) {
        			row[i][puzzle[i][j]] = true;
        			col[j][puzzle[i][j]] = true;
        			area[i/3*3 + j/3][puzzle[i][j]] = true;
        		}
        	}
        }
        
        DFS(0);
	}
	
	static void DFS(int idx) {
		if (idx == SIZE) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < NUM; i++) {
				for (int j = 0; j < NUM; j++) {
					sb.append(puzzle[i][j]);
				}
				sb.append('\n');
			}
			
			System.out.print(sb.toString());
			System.exit(0);
		}
		
		int x = idx / NUM;
		int y = idx % NUM;
		
		if (puzzle[x][y] == 0) {
			for (int i = 1; i <= NUM; i++) {
				if (!row[x][i] && !col[y][i] && !area[x/3*3 + y/3][i]) {
					row[x][i] = true;
					col[y][i] = true;
					area[x/3*3 + y/3][i] = true;
					puzzle[x][y] = i;
					DFS(idx + 1);
					puzzle[x][y] = 0;
					row[x][i] = false;
					col[y][i] = false;
					area[x/3*3 + y/3][i] = false;
				}
			}	
		} else {
			DFS(idx + 1);
		}			
	}
	
}