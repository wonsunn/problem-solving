import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = 987654321;
    static int n, m, answer = INF;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, -1, 0, 1, 0};
    static int[] dy = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    n = Integer.parseInt(br.readLine());
	    map = new int[n][n];
	    visited = new boolean[n][n];

	    for (int i = 0; i < n; i++) {
	        st = new StringTokenizer(br.readLine());
	        for (int j = 0; j < n; j++) {
	            map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

	    DFS(0, 0);
        System.out.println(answer);
    }

    static void DFS(int cnt, int sum) {
        if (cnt == 3) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                boolean flag = true;
                int cost = 0;
                for (int k = 0; k < 5; k++) {
                    if (visited[i + dx[k]][j + dy[k]]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int k = 0; k < 5; k++) {
                        visited[i + dx[k]][j + dy[k]] = true;
                        cost += map[i + dx[k]][j + dy[k]];
                    }
                    
                    DFS(cnt + 1, sum + cost);

                    for (int k = 0; k < 5; k++)
                        visited[i + dx[k]][j + dy[k]] = false;
                }
            }
        }
    }
}