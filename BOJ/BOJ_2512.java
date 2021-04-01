import java.io.*;
import java.util.*;

public class BOJ_2512 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] money = new int[n];
		st = new StringTokenizer(br.readLine());
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, money[i]);
		}
		int m = Integer.parseInt(br.readLine());
		
		int left = 0, right = max, res = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			
			long sum = 0;
			for (int i = 0; i < n; i++) {
				if (money[i] > mid) sum += mid;
				else sum += money[i];
			}
			
			if (sum > m) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
				res = mid;
			}
		}
		
		System.out.println(res);
	}

}