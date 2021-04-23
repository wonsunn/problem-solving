import java.io.*;
import java.util.*;

public class BOJ_2458 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] taller = new boolean[n+1][n+1];
        int res = 0;
        	
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
       		int b = Integer.parseInt(st.nextToken());
       		taller[a][b] = true;
       	}
        	
       	FloydWarshall(taller, n);
        	
       	for (int i = 1; i <= n; i++) {
       		int cnt = 0;
       		for (int j = 1; j <= n; j++) {
       			if (i == j) continue;
       			if (taller[i][j] || taller[j][i]) cnt++;
       		}
        	
        	if (cnt == n-1) res++;
        }
        	
        System.out.println(res);
	}
	
	static void FloydWarshall(boolean[][] arr, int n) {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (arr[i][k] && arr[k][j])
						arr[i][j] = true;
				}
			}
		}
	}

}