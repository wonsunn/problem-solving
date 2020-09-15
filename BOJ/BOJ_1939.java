import java.io.*;
import java.util.*;

class Main {

    static int n, m, i1, i2, maxCost = Integer.MIN_VALUE;
    static List<Info>[] island;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited;

    private static class Info {
        int dest, cost;

        Info (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        island = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            island[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            island[tmp[0]].add(new Info(tmp[1], tmp[2]));
            island[tmp[1]].add(new Info(tmp[0], tmp[2]));

            maxCost = Math.max(tmp[2], maxCost);
        }

        st = new StringTokenizer(br.readLine());
        i1 = Integer.parseInt(st.nextToken()); i2 = Integer.parseInt(st.nextToken());

        int start = 0, end = maxCost;
        while (start <= end) {
            int mid = (start + end) / 2;

            Arrays.fill(visited, false);
            q.clear();

            if (BFS(mid))
                start = mid + 1;
            else
                end = mid - 1;
        }

        System.out.println(end);
    }

    static boolean BFS(int cost) {
        q.add(i1);
        visited[i1] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == i2)
                return true;

            for (int i = 0; i < island[cur].size(); i++) {
                if (!visited[island[cur].get(i).dest] && cost <= island[cur].get(i).cost) {
                    q.add(island[cur].get(i).dest);
                    visited[island[cur].get(i).dest] = true;
                }
            }
        }

        return false;
    }
}