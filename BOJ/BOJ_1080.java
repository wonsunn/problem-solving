import java.io.*;
import java.util.*;

class Main {

    static int n, m, cnt = 0;
    static char[][] A, B;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new char[n][m];
        B = new char[n][m];

        for (int i = 0; i < n; i++)
            A[i] = br.readLine().toCharArray();
        for (int i = 0; i < n; i++)
            B[i] = br.readLine().toCharArray();

        boolean change = false;
        if (n >= 3 && m >= 3) {
            for (int i = 0; i <= n - 3; i++) {
                for (int j = 0; j <= m - 3; j++) {
                    if (A[i][j] != B[i][j]) {
                        changeElement(i, j);
                        cnt++;
                    }

                    if (isSameMatrix()) {
                        change = true;
                        break;
                    }
                }

                if (change)
                    break;
            }
        }
        else {
            change = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (A[i][j] != B[i][j]) {
                        change = false;
                    }
                }
            }
        }

        if (change) System.out.println(cnt);
        else System.out.println(-1);
    }

    /*
    3 X 3 부분 행렬 전체를 뒤집는 함수
     */
    static void changeElement(int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (A[i][j] == '0') A[i][j] = '1';
                else A[i][j] = '0';
            }
        }
    }

    /*
    뒤집힌 행렬 A와 B가 같은지 여부를 판별하는 함수
     */
    static boolean isSameMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j])
                    return false;
            }
        }

        return true;
    }
}