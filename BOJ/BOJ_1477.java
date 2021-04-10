import java.io.*;
import java.util.*;

public class BOJ_1477 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
	
		int[] loc = new int[n + 2];
		
		loc[0] = 0; loc[1] = l;
		st = new StringTokenizer(br.readLine());
		for (int i = 2; i < n + 2; i++) 
			loc[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(loc);
		
		int res = 0;
		int left = 0, right = l;
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = 0;
			
			for (int i = 1; i < n + 2; i++) 
				cnt += (loc[i] - loc[i - 1] - 1) / mid;
			
			if (cnt <= m) {
				right = mid - 1;
				res = mid;
			}
			else {
				left = mid + 1;
			}
		}
		
		System.out.println(res);
	}

}