import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[][] graph;
    static int[] route;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); m = Integer.parseInt(br.readLine());

        route = new int[m];
        graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (i == j)
                    graph[i][j] = 1;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            route[i] = Integer.parseInt(st.nextToken());

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) continue;

                    if (graph[i][j] == 0) {
                        if (graph[i][k] == 1 && graph[k][j] == 1)
                            graph[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < m - 1; i++) {
            if (graph[route[i]][route[i + 1]] == 0) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }
}
