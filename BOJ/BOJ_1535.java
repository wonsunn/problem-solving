import java.io.*;
import java.util.*;

class Main {

    static int n, max = Integer.MIN_VALUE;
    static int[][] people;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        people = new int[n + 1][2];

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++)
                people[j][i] = Integer.parseInt(st.nextToken());
        }

        DFS(0, 0, 0);
        System.out.println(max);
    }

    static void DFS(int idx, int hp, int sum) {
        if (idx == n && hp < 100) {
            max = Math.max(max, sum);
        }

        if (idx < n) {
            DFS(idx + 1, hp + people[idx + 1][0], sum + people[idx + 1][1]);
            DFS(idx + 1, hp, sum);
        }
    }
}
