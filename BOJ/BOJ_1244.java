import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[] switches;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(in.readLine());
		switches = new int[n + 1];
		
		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= n; i++)
			switches[i] = Integer.parseInt(st.nextToken());
		
		int student = Integer.parseInt(in.readLine());
		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(in.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int which = Integer.parseInt(st.nextToken());
			changeSwitch(gender, which);
		}
		
		for (int i = 1; i <= n; i++) {
			if (i % 20 == 0) System.out.println(switches[i] + " ");
			else System.out.print(switches[i] + " ");
		}
	}
	
	private static void changeSwitch(int gender, int which) {
		if (gender == 1) {
			for (int j = which; j <= n; j += which) 
				change(j);
		}
		else {
			change(which);
			int idx = 1;
			while (true) {
				if (which - idx < 1 || which + idx > n || 
						switches[which - idx] != switches[which + idx]) 
					break;
				
				change(which - idx);
				change(which + idx);
				idx++;
			}
		}
	}
	
	private static void change(int i) {
		switches[i] = (switches[i] == 1) ? 0 : 1;
	}
}