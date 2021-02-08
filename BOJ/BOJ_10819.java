import java.io.*;
import java.util.*;

public class Main {

    static int n, max;
    static int[] A, res;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n];
        res = new int[n];
        isSelected = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            A[i] = Integer.parseInt(st.nextToken());

        solve(0);
        System.out.println(max);
    }

    static void solve(int idx) {
        if (idx == n) {
            int sum = 0;
            for (int i = 1; i < n; i++) {
                sum += Math.abs(res[i] - res[i - 1]);
            }
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            res[idx] = A[i];
            solve(idx + 1);
            isSelected[i] = false;
        }
    }
}