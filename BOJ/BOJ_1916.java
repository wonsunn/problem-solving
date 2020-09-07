import java.io.*;
import java.util.*;

class Main {

    static int n, m, start, dest;
    static List<Info>[] map;
    static int[] dis;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dis = new int[n + 1];
        visited = new boolean[n + 1];

        map = new ArrayList[n + 1];
        for (int i = 0; i < map.length; i++)
            map[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split(" ");
            map[Integer.parseInt(str[0])].add(new Info(Integer.parseInt(str[1]), Integer.parseInt(str[2])));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        for (int i = 1; i <= n; i++) {
            int min = 0;

            for (int j = 1; j <= n; j++) {
                if (dis[min] > dis[j] && !visited[j])
                    min = j;
            }

            visited[min] = true;

            for (int j = 0; j < map[min].size(); j++) {
                if (dis[min] + map[min].get(j).cost < dis[map[min].get(j).node])
                    dis[map[min].get(j).node] = dis[min] + map[min].get(j).cost;
            }
        }

        System.out.println(dis[dest]);
    }
}

class Info {
    int node, cost;

    Info (int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}