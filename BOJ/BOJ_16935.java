import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16935 {
	
	static int n, m, r;
	static int[][] arr, newArr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < r; i++) {
			rotate(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void rotate(int option) {
		switch(option) {
		case 1:
			for (int i = 0; i < arr[0].length; i++) 
				for (int j = 0; j < arr.length / 2; j++) 
					swap1(i, j, arr.length - j - 1);
			break;
		case 2:
			for (int i = 0; i < arr.length; i++) 
				for (int j = 0; j < arr[0].length / 2; j++) 
					swap2(i, j, arr[0].length - j - 1);
			break;
		case 3:
			newArr = new int[arr[0].length][arr.length];
			for (int i = 0; i < arr.length; i++) 
				for (int j = 0; j < arr[0].length; j++) 
					newArr[j][arr.length - 1 - i] = arr[i][j];
				
			copyArray();
			break;
		case 4:
			newArr = new int[arr[0].length][arr.length];
			for (int i = 0; i < arr.length; i++) 
				for (int j = 0; j < arr[0].length; j++) 
					newArr[arr[0].length - 1 - j][i] = arr[i][j];
				
			copyArray();
			break;
		case 5:
			int x = arr.length;
			int y = arr[0].length;
			newArr = new int[x][y];
			// 1 -> 2
			for (int i = 0; i < x / 2; i++) 
				for (int j = 0; j < y / 2; j++) 
					newArr[i][j + y / 2] = arr[i][j];
			// 2 -> 3
			for (int i = 0; i < x / 2; i++) 
				for (int j = y / 2; j < y; j++)
					newArr[i + x / 2][j] = arr[i][j];
			// 3 -> 4
			for (int i = x / 2; i < x; i++) 
				for (int j = y / 2; j < y; j++)
					newArr[i][j - y / 2] = arr[i][j];
			// 4 -> 1
			for (int i = x / 2; i < x; i++) 
				for (int j = 0; j < y / 2; j++)
					newArr[i - x / 2][j] = arr[i][j];
			
			copyArray();
			break;
		case 6:
			x = arr.length;
			y = arr[0].length;
			newArr = new int[x][y];
			// 1 -> 4
			for (int i = 0; i < x / 2; i++) 
				for (int j = 0; j < y / 2; j++) 
					newArr[i + x / 2][j] = arr[i][j];
			// 2 -> 1
			for (int i = 0; i < x / 2; i++) 
				for (int j = y / 2; j < y; j++)
					newArr[i][j - y / 2] = arr[i][j];
			// 3 -> 2
			for (int i = x / 2; i < x; i++) 
				for (int j = y / 2; j < y; j++)
					newArr[i - x / 2][j] = arr[i][j];
			// 4 -> 3
			for (int i = x / 2; i < x; i++) 
				for (int j = 0; j < y / 2; j++)
					newArr[i][j + y / 2] = arr[i][j];
			
			copyArray();
			break;
		}
	}
	
	/*
	 * 1번 연산 - 상하반전
	 */
	static void swap1(int c, int i, int j) {
		int tmp = arr[i][c];
		arr[i][c] = arr[j][c];
		arr[j][c] = tmp;
	}
	
	/*
	 * 2번 연산 - 좌우반전
	 */
	static void swap2(int r, int i, int j) {
		int tmp = arr[r][i];
		arr[r][i] = arr[r][j];
		arr[r][j] = tmp;
	}
	
	static void copyArray() {
		arr = new int[newArr.length][newArr[0].length];
		for (int i = 0; i < newArr.length; i++)
			for (int j = 0; j < newArr[0].length; j++)
				arr[i][j] = newArr[i][j];
	}
}