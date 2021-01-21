import java.io.*;
import java.util.*;

public class Main {

    static int n, startNode, min = Integer.MAX_VALUE;
    static int[][] cost;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                cost[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            startNode = i;
            Arrays.fill(visited, false);
            visited[i] = true;
            DFS(0, 0, i);
        }

        System.out.println(min);
    }

    static void DFS(int cnt, int sum, int cur) {
        if (cnt == n - 1) {
            if (cost[cur][startNode] != 0) {
                min = Math.min(min, sum + cost[cur][startNode]);    
                return;
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && cost[cur][i] != 0) {
                visited[i] = true;
                DFS(cnt + 1, sum + cost[cur][i], i);
                visited[i] = false;
            }
        }
    }
}