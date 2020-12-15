import java.io.*;
import java.util.*;

class Main {

    static int result = Integer.MAX_VALUE, cnt = 0;

    static int[][] board = new int[10][10];
    static int[] count = {0, 5, 5, 5, 5, 5}; // 각 색종이의 사용 개수를 체크하기 위함

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0);

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    static void solve (int x, int y) {
        // 다음 줄 검사
        if (y == 10) {
            solve(x + 1, 0);
            return;
        }
        // 마지막 줄까지 끝냈으므로 개수 확인
        if (x == 10) {
            result = Math.min(result, cnt);
            return;
        }
        // 종이 못 덮으므로 다음 요소 검사
        if (board[x][y] == 0) {
            solve(x, y + 1);
            return;
        }

        for (int len = 5; len > 0; len--) {
            // 종이를 모두 사용했거나, 범위를 벗어날 때 검사 x
            if (count[len] == 0 || x + len > 10 || y + len > 10) {
                continue;
            }

            boolean flag = true;

            for (int i = x; i < x + len; i++) {
                for (int j = y; j < y + len; j++) {
                    if (board[i][j] == 0) {
                        flag = false;
                        break;
                    }
                }
                if (!flag)
                    break;
            }
            // 모두 1이 아니므로 못 붙임
            if (!flag)
                continue;

            // 종이 붙임
            count[len]--;
            cnt++;
            for (int i = x; i < x + len; i++) {
                for (int j = y; j < y + len; j++)
                    board[i][j] = 0;
            }

            solve(x, y + len);

            // 종이 다시 떼는 작업
            count[len]++;
            cnt--;
            for (int i = x; i < x + len; i++) {
                for (int j = y; j < y + len; j++)
                    board[i][j] = 1;
            }
        }
    }
}