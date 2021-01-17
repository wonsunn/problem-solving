import java.io.*;
import java.util.*;

public class Main {

    static int n, m, ans = 0;
    static boolean[] visited;
    static List<Integer>[] network;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];
        network = new List[n + 1];
        for (int i = 1; i <= n; i++) network[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            network[c1].add(c2);
            network[c2].add(c1);
        }

        System.out.println(BFS(1));
    }

    static int BFS(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < network[cur].size(); i++) {
                int next = network[cur].get(i);

                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                    ans++;
                }
            }
        }

        return ans;
    }
}