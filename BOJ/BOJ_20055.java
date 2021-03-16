import java.io.*;
import java.util.*;

public class BOJ_20055 {

	static int n, k, zeroCnt, stage;
	static int[][] belt;
	static boolean[][] isRobot;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        belt = new int[2][n];
        isRobot = new boolean[2][n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) belt[0][i] = Integer.parseInt(st.nextToken());
        for (int i = n - 1; i >= 0; i--) belt[1][i] = Integer.parseInt(st.nextToken());
        
        while (true) {
        	stage++;
        	rotateBelt();
        	locateRobot();
        	
        	if (zeroCnt >= k) {
        		System.out.println(stage);
        		break;
        	}
        }
	}
	
	static void locateRobot() {
		for (int i = n - 1; i >= 0; i--) {
			if (isRobot[0][i]) {
				if (i == n - 1) {
					isRobot[0][i] = false;
					continue;
				}
				
				if (isRobot[0][i + 1] || belt[0][i + 1] == 0) continue;
				
				isRobot[0][i] = false;
				isRobot[0][i + 1] = true;
				if (--belt[0][i + 1] == 0)
					zeroCnt++;
			}
		}
		
		if (belt[0][0] != 0 && !isRobot[0][0]) {
			isRobot[0][0] = true;
			if (--belt[0][0] == 0) zeroCnt++;
		}
	}
	
	static void rotateBelt() {
		int tmp_down = belt[0][n - 1], tmp_up = belt[1][0];
		boolean tmp_down2 = isRobot[0][n - 1], tmp_up2 = isRobot[1][0];
		
		for (int i = n - 1; i > 0; i--) {
			belt[0][i] = belt[0][i - 1];
			isRobot[0][i] = isRobot[0][i - 1];
		}
		belt[0][0] = tmp_up;
		isRobot[0][0] = tmp_up2;
		
		for (int i = 0; i < n - 1; i++) {
			belt[1][i] = belt[1][i + 1];
			isRobot[1][i] = isRobot[1][i + 1];
		}
		belt[1][n - 1] = tmp_down;
		if (tmp_down2) tmp_down2 = !tmp_down2; 
		isRobot[1][n - 1] = tmp_down2;
	}
}