import java.io.*;
import java.util.*;

class Main {

    static int n, m, x;
    static int[] fDist, bDist;
    static boolean[] visited;
    static ArrayList<Info>[] fGraph, bGraph;

    private static class Info {
        int node, cost;

        Info (int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        fGraph = new ArrayList[n + 1];
        bGraph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            fGraph[i] = new ArrayList<>();
            bGraph[i] = new ArrayList<>();
        }

        fDist = new int[n + 1];
        bDist = new int[n + 1];
        
        Arrays.fill(fDist, Integer.MAX_VALUE);
        Arrays.fill(bDist, Integer.MAX_VALUE);
        fDist[x] = 0; bDist[x] = 0;
        
        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            fGraph[tmp[0]].add(new Info(tmp[1], tmp[2]));
            bGraph[tmp[1]].add(new Info(tmp[0], tmp[2]));
        }

        solve(fDist, fGraph);
        solve(bDist, bGraph);

        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (res < fDist[i] + bDist[i])
                res = fDist[i] + bDist[i];
        }

        System.out.println(res);
    }

    static void solve(int[] dist, ArrayList<Info>[] graph) {
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            int min = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[min] > dist[j] && !visited[j])
                    min = j;
            }

            visited[min] = true;

            for (int j = 0; j < graph[min].size(); j++) {
                int node = graph[min].get(j).node;
                int cost = graph[min].get(j).cost;

                if (dist[min] + cost < dist[node])
                    dist[node] = dist[min] + cost;
            }
        }
    }
}
