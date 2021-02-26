import java.io.*;

public class BOJ_2661 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		DFS(0, "", n);
	}
	
	static boolean DFS(int cnt, String str, int n) {
		for (int i = 0; i < cnt / 2; i++) {
			int div = 2*i + 1;
			String tmp1 = str.substring(cnt - 1 - div, cnt - 1 - i);
			String tmp2 = str.substring(cnt - 1 - i, cnt);
			
			if (tmp1.equals(tmp2)) {
				return false;
			}
		}
		
		if (cnt == n) {
			System.out.println(str);
			return true;
		}
		
		for (int i = 1; i <= 3; i++) {
			if (DFS(cnt + 1, str.concat(String.valueOf(i)), n))
				return true;
		}
		
		return false;
	}

}
