import java.io.*;
import java.util.*;

import static java.lang.System.exit;

class Main {

    static int n;
    static char[][] s;
    static int[] select;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        s = new char[n + 1][n + 1];
        select = new int[n + 1];

        char[] tmp = br.readLine().toCharArray();
        int idx = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++)
                s[i][j] = tmp[idx++];
        }

        DFS(0);
    }

    static boolean isValid(int idx) {
        int sum = 0;
        for (int i = idx; i > 0; i--) {
            sum += select[i];
            if (s[i][idx] == '+' && sum <= 0) return false;
            if (s[i][idx] == '-' && sum >= 0) return false;
            if (s[i][idx] == '0' && sum != 0) return false;
        }

        return true;
    }

    static void DFS(int level) {
        if (level == n) {
            for (int i = 1; i <= n; i++)
                System.out.print(select[i] + " ");
            
            exit(0);
        }

        for (int i = -10; i <= 10; i++) {
            select[level + 1] = i;
            if (isValid(level + 1)) 
                DFS(level + 1);
        }
    }
}