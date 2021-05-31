import java.io.*;
import java.util.*;

public class BOJ_14267 {

    static int n, m;
    static List<Integer>[] graph;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();
        res = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parents = Integer.parseInt(st.nextToken());
            if (parents == -1) continue;
            graph[parents].add(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            res[start] += val;
        }

        DFS(1);

        for (int i = 1; i <= n; i++)
            sb.append(res[i]).append(' ');
        System.out.println(sb);
    }

    static void DFS(int node) {
        for (int next : graph[node]) {
            res[next] += res[node];
            DFS(next);
        }
    }
}