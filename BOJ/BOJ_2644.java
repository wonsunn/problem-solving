import java.io.*;
import java.util.*;

class Main {

    static int n, a, b, m;
    static int[][] graph;
    static int[] depth;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];
        depth = new int[n + 1];
        Arrays.fill(depth, -1);

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[p][c] = 1;
            graph[c][p] = 1;
        }

        System.out.println(BFS(a));
    }

    static int BFS(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        depth[node] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == b)
                return depth[cur];

            for (int i = 1; i <= n; i++) {
                if (graph[cur][i] == 1 && depth[i] == -1) {
                    q.add(i);
                    depth[i] = depth[cur] + 1;
                }
            }
        }

        return -1;
    }
}
