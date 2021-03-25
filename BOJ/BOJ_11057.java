import java.io.*;

public class BOJ_11057 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[10][n + 1];
		
		for (int i = 0; i <= n; i++) dp[0][i] = 1;
		for (int i = 0; i < 10; i++) dp[i][0] = 1;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < 10; j++) 
				dp[j][i] = (dp[j - 1][i] + dp[j][i - 1]) % 10007;
		}
		
		System.out.println(dp[9][n]);
	}
}