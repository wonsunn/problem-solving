import java.io.*;
import java.util.*;

class Main {

    static int n;
    static int[] s;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        s = new int[n];

        st = new StringTokenizer(br.readLine());
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
            maxVal += s[i];
        }
        check = new boolean[maxVal + 2]; // 최댓값까지 모든 수가 존재할 수 있기 때문에 최댓값 + 1로 지정

        DFS(0, 0);

        for (int i = 1; i <= maxVal + 1; i++) {
            if (!check[i]) {
                System.out.println(i);
                break;
            }
        }
    }

    static void DFS(int level, int sum) {
        if (level == n) {
            check[sum] = true;
            return;
        }

        DFS(level + 1, sum + s[level]);
        DFS(level + 1, sum);
    }
}