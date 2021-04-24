import java.io.*;
import java.util.*;

public class BOJ_17143 {

	static int R, C, m;
	static int[][] map;
	static int[][] shark;
	
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        shark = new int[m+1][6];
        for (int i = 1; i <= m; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < 5; j++) {
        		shark[i][j] = Integer.parseInt(st.nextToken());
        	}
        	shark[i][5] = 1;
        	map[shark[i][0]][shark[i][1]] = i;
        }
        
        int loc = 0, res = 0;
        while (true) {
        	if (++loc > C) break;
        	//상어 잡기
        	for (int i = 1; i <= R; i++) {
        		if (map[i][loc] != 0) {
        			int s = map[i][loc];
        			res += shark[s][4];
        			shark[s][5] = 0;
        			break;
        		}
        	}
        	
        	for (int[] row : map) Arrays.fill(row, 0);
        	
        	//상어 이동
        	for (int i = 1; i <= m; i++) {
        		if (shark[i][5] != 0) {
        			move(i);
        		}
        	}
        }
        
        System.out.println(res); 
	}
	
	static void move(int sh) {
		int r = shark[sh][0], c = shark[sh][1], s = shark[sh][2], dir = shark[sh][3];
		int rot = 0;
		if (dir == 1 || dir == 2) rot = (R - 1) * 2;
		else rot = (C - 1) * 2;
		
		if (s >= rot) s %= rot;
		
		while (s-- > 0) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if (nr < 1 || nr > R || nc < 1 || nc > C) {
				nr -= dr[dir];
				nc -= dc[dir];
				dir = (dir % 2 == 0) ? dir - 1 : dir + 1;
				nr += dr[dir];
				nc += dc[dir];
			}
			
			r = nr;
			c = nc;
		}
		
		boolean alive = true;
		if (map[r][c] != 0) {
			int prev = map[r][c];
			if (shark[prev][4] < shark[sh][4]) {
				shark[prev][5] = 0;
			} else {
				shark[sh][5] = 0;
				alive = false;
			}
		}
		
		if (alive) {
			shark[sh][0] = r;
			shark[sh][1] = c;
			shark[sh][3] = dir;
			map[r][c] = sh;	
		}
	}

}