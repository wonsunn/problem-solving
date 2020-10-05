import java.io.*;
import java.util.*;

class Main {

    static int n, m;
    static int[][] graph;
    static int[] route, parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); m = Integer.parseInt(br.readLine());

        route = new int[m];
        parent = new int[n + 1];
        graph = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());

                if (graph[i][j] == 1)
                    union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++)
            route[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m - 1; i++) {
            if (find(route[i]) != find(route[i + 1])) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int find(int x) {
        while (x != parent[x])
            x = parent[x];

        return x;
    }
}