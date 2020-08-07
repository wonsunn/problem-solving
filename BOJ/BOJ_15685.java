import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static boolean[][] board = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static List<Integer> curve;

    public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

	    n = Integer.parseInt(br.readLine());

	    for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            curve = new ArrayList<>();

            int x = Integer.parseInt(st.nextToken()); int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()); int generation = Integer.parseInt(st.nextToken());

            board[y][x] = true;
            curve.add(dir);
            setDragonCurve(x, y, generation);
        }

        int cnt = 0;
	    for (int i = 0; i <= 99; i++) {
	        for (int j = 0; j <= 99; j++) {
	            if (board[i][j] && board[i+1][j] && board[i][j+1] & board[i+1][j+1])
	                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void setDragonCurve(int nx, int ny, int generation) {
        for (int i = 0; i < generation; i++)
            for (int j = curve.size() - 1; j >= 0; j--)
                curve.add((curve.get(j) + 1) % 4);

        for (int i = 0; i < curve.size(); i++) {
            nx += dx[curve.get(i)]; ny += dy[curve.get(i)];
            board[ny][nx] = true;
        }
    }
}